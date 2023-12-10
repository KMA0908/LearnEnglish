package com.example.learningenglish.fragment

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningenglish.R
import com.example.learningenglish.activity.AddTopicActivity
import com.example.learningenglish.activity.WordTopicActivity
import com.example.learningenglish.adapter.TopicAdapter
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.FragmentTopicBinding
import org.koin.android.ext.android.inject

class TopicFragment : BaseFragment<FragmentTopicBinding>() {
    private lateinit var topicAdapter: TopicAdapter
    private val sqlHelper: SQLHelper by inject()
    override fun getVM(): ViewModel {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        val listTopic = sqlHelper.getAllTopic()
        listTopic.let {
            topicAdapter = TopicAdapter(it) { topic ->
                val intent = Intent(activity, WordTopicActivity::class.java)
                intent.putExtra(WordTopicActivity.TOPIC_WORD, topic.id)
                intent.putExtra(WordTopicActivity.TOPIC_DATA, topic)
                startActivity(intent)
            }
            binding.rcvTopic.adapter = topicAdapter
            binding.rcvTopic.layoutManager = LinearLayoutManager(activity)
        }
        binding.ivAddTopic.setOnClickListener {
            val intent = Intent(activity, AddTopicActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_topic
    }
    override fun onResume() {
        super.onResume()
        val listTopic = sqlHelper.getAllTopic()
        topicAdapter.setListTopic(listTopic)
    }
}
