package com.example.learningenglish.activity

import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.databinding.ActivityEditProfileBinding
import com.example.learningenglish.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileActivity : BaseActivity<ActivityEditProfileBinding, SplashViewModel>() {
    companion object {
        internal val TAG = SplashActivity::class.java.name
    }
    private val viewModel: SplashViewModel by viewModel()

    override fun getVM(): SplashViewModel = viewModel

    override fun initViews() {

    }

    override fun getLayoutId() = R.layout.activity_edit_profile
}