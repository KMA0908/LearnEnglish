package com.example.learningenglish.activity

import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.databinding.ActivityAddFolderBinding
import com.example.learningenglish.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFolderActivity : BaseActivity<ActivityAddFolderBinding, SplashViewModel>() {
    companion object {
        internal val TAG = SplashActivity::class.java.name
    }
    private val viewModel: SplashViewModel by viewModel()

    override fun getVM(): SplashViewModel = viewModel

    override fun initViews() {

    }

    override fun getLayoutId() = R.layout.activity_add_folder
}