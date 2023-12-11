package com.example.learningenglish.fragment

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningenglish.R
import com.example.learningenglish.activity.WordTopicActivity
import com.example.learningenglish.adapter.TopicAdapter
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentPublicBinding
import com.example.learningenglish.model.Topic
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.android.inject

class PublicFragment : BaseFragment<FragmentPublicBinding>() {
    companion object {
        internal val TOPIC_COLLECTION = "topic"
    }
    private lateinit var topicAdapter: TopicAdapter
    private val firestore: FirebaseFirestore by inject()
    private val firebaseAuth: FirebaseAuth by inject()
    private lateinit var list: MutableList<Topic>

    override fun getVM(): ViewModel {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        fetchPublicTopic()

        list = mutableListOf<Topic>()

        topicAdapter = TopicAdapter(list){
//            val intent = Intent(activity, WordTopicActivity::class.java)
//            intent.putExtra("TOPIC_WORD", it)
//            startActivity(intent)
        }
        binding.rcvPublic.adapter = topicAdapter
        binding.rcvPublic.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_public
    }

    fun fetchPublicTopic() {
        firestore.collection(TOPIC_COLLECTION).whereNotEqualTo("userId", firebaseAuth.uid)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    for(doc in it.result.documents) {
                        doc.data?.let { data ->
                            Log.d("minh", "fetchPublicTopic: ${data.values} ${data.keys}")
                            val topic = Topic(data.get("id").toString().toInt(), data.get("name").toString(), data.get("total").toString().toInt(), 1)
                            list.add(topic)
                        }
                    }
                    topicAdapter.notifyDataSetChanged()
                }
            }
    }
}
