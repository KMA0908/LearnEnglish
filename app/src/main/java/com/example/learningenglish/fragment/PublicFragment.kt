package com.example.learningenglish.fragment

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningenglish.R
import com.example.learningenglish.activity.WordTopicActivity
import com.example.learningenglish.adapter.TopicAdapter
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentPublicBinding
import com.example.learningenglish.model.Topic

class PublicFragment : BaseFragment<FragmentPublicBinding>() {
    private lateinit var topicAdapter: TopicAdapter
    override fun getVM(): ViewModel {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        val listTopic = arrayListOf<Topic>()
        topicAdapter = TopicAdapter(listTopic){
            val intent = Intent(activity, WordTopicActivity::class.java)
            intent.putExtra("TOPIC_WORD", it)
            startActivity(intent)
        }
        binding.rcvPublic.adapter = topicAdapter
        binding.rcvPublic.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_public
    }
}
