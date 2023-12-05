package com.example.learningenglish.activity

import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.ActivityAddFolderBinding
import com.example.learningenglish.model.Folder
import com.example.learningenglish.viewmodel.SplashViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFolderActivity : BaseActivity<ActivityAddFolderBinding, SplashViewModel>() {
    private val sqlHelper: SQLHelper by inject()
    companion object {
        internal val TAG = SplashActivity::class.java.name
    }
    private val viewModel: SplashViewModel by viewModel()

    override fun getVM(): SplashViewModel = viewModel

    override fun initViews() {
        binding.tvDone.setOnClickListener {
            sqlHelper.addFolder(Folder(binding.edtNameFolder.text.toString()))
            finish()
        }
    }

    override fun getLayoutId() = R.layout.activity_add_folder
}