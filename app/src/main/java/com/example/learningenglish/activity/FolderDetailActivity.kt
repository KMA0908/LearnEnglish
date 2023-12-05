package com.example.learningenglish.activity


import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningenglish.R
import com.example.learningenglish.adapter.TopicAdapter
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.databinding.ActivityFolderDetailBinding
import com.example.learningenglish.model.Topic
import com.example.learningenglish.viewmodel.FolderViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FolderDetailActivity : BaseActivity<ActivityFolderDetailBinding, FolderViewModel>() {
    companion object {
        internal val FOLDER_ID = "FOLDER_ID"
        internal val TAG = FolderViewModel::class.java.name
    }

    private lateinit var topicAdapter: TopicAdapter
    private val viewModel: FolderViewModel by viewModel()

    override fun getVM(): FolderViewModel = viewModel

    override fun initViews() {
        val listTopic = arrayListOf<Topic>()
        topicAdapter = TopicAdapter(listTopic) {
            val intent = Intent(this@FolderDetailActivity, WordTopicActivity::class.java)
            intent.putExtra("TOPIC_WORD", it)
            startActivity(intent)
        }
        binding.rcvTopicFolder.adapter = topicAdapter
        binding.rcvTopicFolder.layoutManager = LinearLayoutManager(this)
    }

    override fun getLayoutId() = R.layout.activity_splash
}