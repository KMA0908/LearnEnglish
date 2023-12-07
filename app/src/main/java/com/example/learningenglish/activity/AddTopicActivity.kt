package com.example.learningenglish.activity

import android.widget.ArrayAdapter
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.ActivityAddTopicBinding
import com.example.learningenglish.model.Topic
import com.example.learningenglish.viewmodel.SplashViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.schedulers.Schedulers.single
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTopicActivity: BaseActivity<ActivityAddTopicBinding, SplashViewModel>() {
    private val sqlHelper: SQLHelper by inject()
    companion object {
        internal val TAG = SplashActivity::class.java.name
        internal val TOPIC_COLLECTION = "topic"
    }
    private val viewModel: SplashViewModel by viewModel()
    private var modeTopic = 0
    private val firestore: FirebaseFirestore by inject()
    private val firebaseAuth: FirebaseAuth by inject()

    override fun getVM(): SplashViewModel = viewModel

    override fun initViews() {
        binding.rgMode.setOnCheckedChangeListener { _, i ->
            when (i) {
                binding.btPrivate.id -> modeTopic = 0
                binding.btPublic.id -> modeTopic = 1
                else -> modeTopic = 0
            }
        }
        binding.tvDone.setOnClickListener {
            val topic = Topic(binding.edtNameTopic.text.toString(),0, modeTopic)
            sqlHelper.addTopic(topic, binding.spinnerFolder.selectedItem.toString().toInt())
            // Topic la public thi day len firebase
            if (topic.mode == 1) {
                val data = hashMapOf(
                    "name" to topic.name,
                    "total" to topic.total,
                    "id" to topic.id,
                    "userId" to firebaseAuth.uid
                )
                firestore.collection(TOPIC_COLLECTION).add(data).addOnCompleteListener {
                    //

                }
            }
            finish()
        }
        val list: ArrayList<String> = ArrayList()
        for (i in 0 until sqlHelper.getAllFolder().size) {
            list.add(sqlHelper.getAllFolder()[i].id.toString())
        }
        val arr = list.toTypedArray()
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, arr
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFolder.adapter = adapter
    }

    override fun getLayoutId() = R.layout.activity_add_topic
}