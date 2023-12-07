package com.example.learningenglish.activity


import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningenglish.R
import com.example.learningenglish.adapter.TopicAdapter
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.ActivityFolderDetailBinding
import com.example.learningenglish.model.Topic
import com.example.learningenglish.viewmodel.FolderViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FolderDetailActivity : BaseActivity<ActivityFolderDetailBinding, FolderViewModel>() {
    private val sqlHelper: SQLHelper by inject()
    companion object {
        internal val FOLDER_ID = "FOLDER_ID"
        internal val TAG = FolderViewModel::class.java.name
    }

    private lateinit var topicAdapter: TopicAdapter
    private var folderId: Int = 0
    private val viewModel: FolderViewModel by viewModel()

    override fun getVM(): FolderViewModel = viewModel

    override fun initViews() {
        folderId = intent.getIntExtra(FOLDER_ID,0)
        val listTopic = sqlHelper.searchByTopicByFolderId(folderId)
        listTopic.let {
            topicAdapter = TopicAdapter(it) { topic ->
                val intent = Intent(this@FolderDetailActivity, WordTopicActivity::class.java)
                intent.putExtra("TOPIC_WORD", topic)
                startActivity(intent)
            }
            binding.rcvTopicFolder.adapter = topicAdapter
            binding.rcvTopicFolder.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onResume() {
        super.onResume()
        val listTopic = sqlHelper.searchByTopicByFolderId(folderId)

        topicAdapter.setListTopic(listTopic)
    }
    override fun getLayoutId() = R.layout.activity_folder_detail
}