package com.example.learningenglish.activity

import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.ActivityAddFolderBinding
import com.example.learningenglish.databinding.ActivityAddTopicBinding
import com.example.learningenglish.model.Folder
import com.example.learningenglish.model.Topic
import com.example.learningenglish.viewmodel.SplashViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTopicActivity: BaseActivity<ActivityAddTopicBinding, SplashViewModel>() {
    private val sqlHelper: SQLHelper by inject()
    companion object {
        internal val TAG = SplashActivity::class.java.name
    }
    private val viewModel: SplashViewModel by viewModel()
    private var modeTopic = 0
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
            sqlHelper.addTopic(Topic(binding.edtNameTopic.text.toString(),0, modeTopic))
            finish()
        }
    }

    override fun getLayoutId() = R.layout.activity_add_topic
}