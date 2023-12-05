package com.example.learningenglish.fragment

import androidx.lifecycle.ViewModel
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentFolderBinding
import com.example.learningenglish.viewmodel.FolderViewModel
import com.example.learningenglish.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FolderFragment : BaseFragment<FragmentFolderBinding>() {
    override fun getVM(): ViewModel = viewModel
    private val viewModel: FolderViewModel by viewModel()
    override fun initViews() {
       binding.ivAddSub.setOnClickListener {

       }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_folder
    }
}
