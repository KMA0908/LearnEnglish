package com.example.learningenglish.activity

import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.ActivityAddWordTopicBinding
import com.example.learningenglish.model.Topic
import com.example.learningenglish.model.Word
import com.example.learningenglish.viewmodel.SplashViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddWordTopicActivity: BaseActivity<ActivityAddWordTopicBinding, SplashViewModel>() {
    private val sqlHelper: SQLHelper by inject()
    private lateinit var topic : Topic
    companion object {
        internal val TAG = SplashActivity::class.java.name
    }
    private val viewModel: SplashViewModel by viewModel()

    override fun getVM(): SplashViewModel = viewModel

    override fun initViews() {
        topic = intent.getSerializableExtra(WordTopicActivity.TOPIC_DATA) as Topic
        binding.tvDone.setOnClickListener {
            sqlHelper.addWord(Word(binding.edtNameWord.text.toString(),binding.edtMeaningWord.text.toString(),
                0),id = intent.getIntExtra(WordTopicActivity.TOPIC_ID,0))
            val index = ++topic.total
            sqlHelper.updateTopic(Topic(topic.id,topic.name,index, topic.mode))
            finish()
        }
    }

    override fun getLayoutId() = R.layout.activity_add_word_topic
}