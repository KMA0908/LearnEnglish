package com.example.learningenglish.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningenglish.R
import com.example.learningenglish.adapter.WordAdapter
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.databinding.ActivityTopicWordBinding
import com.example.learningenglish.model.Word
import com.example.learningenglish.viewmodel.TopicViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordTopicActivity: BaseActivity<ActivityTopicWordBinding, TopicViewModel>() {
    companion object {
        private val TAG = TopicViewModel::class.java.name
    }

    private lateinit var topicWordAdapter: WordAdapter
    private val viewModel: TopicViewModel by viewModel()
    override fun getVM(): TopicViewModel = viewModel

    override fun initViews() {
        val listWordTopic = arrayListOf<Word>()
        topicWordAdapter = WordAdapter(listWordTopic){

        }
        binding.rcvWordTopic.adapter = topicWordAdapter
        binding.rcvWordTopic.layoutManager = LinearLayoutManager(this)
    }

    override fun getLayoutId() = R.layout.activity_topic_word
}