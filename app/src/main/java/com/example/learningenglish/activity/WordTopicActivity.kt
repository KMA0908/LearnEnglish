package com.example.learningenglish.activity

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningenglish.R
import com.example.learningenglish.adapter.WordAdapter
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.ActivityTopicWordBinding
import com.example.learningenglish.model.Topic
import com.example.learningenglish.model.Word
import com.example.learningenglish.viewmodel.TopicViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordTopicActivity: BaseActivity<ActivityTopicWordBinding, TopicViewModel>() {
    companion object {
        private val TAG = TopicViewModel::class.java.name
        internal val TOPIC_WORD = "TOPIC_WORD"
        internal val TOPIC_DATA = "TOPIC_DATA"
        internal val TOPIC_ID = "TOPIC_ID"
    }
    private val sqlHelper: SQLHelper by inject()
    private lateinit var topicWordAdapter: WordAdapter
    private val viewModel: TopicViewModel by viewModel()
    private var topicWordId = 0
    private lateinit var topic : Topic
    override fun getVM(): TopicViewModel = viewModel

    override fun initViews() {
        topicWordId = intent.getIntExtra(TOPIC_WORD,0)
        topic = intent.getSerializableExtra(TOPIC_DATA) as Topic
        val listWordTopic = sqlHelper.searchByWordTopic(topicWordId)
        topicWordAdapter = WordAdapter(listWordTopic){

        }
        binding.rcvWordTopic.adapter = topicWordAdapter
        binding.rcvWordTopic.layoutManager = LinearLayoutManager(this)
        binding.ivAddWordTopic.setOnClickListener {
            val intent = Intent(this@WordTopicActivity, AddWordTopicActivity::class.java)
            intent.putExtra(TOPIC_ID,topicWordId)
            intent.putExtra(TOPIC_DATA,topic)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val listWordTopic = sqlHelper.searchByWordTopic(topicWordId)
        topicWordAdapter.setListWordTopic(listWordTopic)
    }
    override fun getLayoutId() = R.layout.activity_topic_word
}