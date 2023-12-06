package com.example.learningenglish.fragment

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningenglish.R
import com.example.learningenglish.activity.AddFolderActivity
import com.example.learningenglish.activity.FolderDetailActivity
import com.example.learningenglish.adapter.FolderAdapter
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.FragmentFolderBinding
import com.example.learningenglish.viewmodel.FolderViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FolderFragment : BaseFragment<FragmentFolderBinding>() {
    private val sqlHelper: SQLHelper by inject()
    private lateinit var folderAdapter: FolderAdapter
    override fun getVM(): ViewModel = viewModel
    private val viewModel: FolderViewModel by viewModel()
    override fun initViews() {
        val listFolder = sqlHelper.getAllFolder()
        folderAdapter = FolderAdapter(listFolder) {
            val intent = Intent(activity, FolderDetailActivity::class.java)
            intent.putExtra(FolderDetailActivity.FOLDER_ID, it)
            startActivity(intent)
        }
        binding.rcvFolder.adapter = folderAdapter
        binding.rcvFolder.layoutManager = LinearLayoutManager(requireContext())
       binding.ivAddSub.setOnClickListener {
          openAddFolderAct()
       }
    }

    override fun onResume() {
        super.onResume()
        val listFolder = sqlHelper.getAllFolder()
        folderAdapter.setListFolder(listFolder)
    }
    private fun openAddFolderAct() {
        startActivity(Intent(activity,AddFolderActivity::class.java ))
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_folder
    }
}
