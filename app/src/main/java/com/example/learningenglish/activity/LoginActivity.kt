package com.example.learningenglish.activity

import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.databinding.ActivityLoginBinding
import com.example.learningenglish.fragment.LoginFragment
import com.example.learningenglish.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    companion object {
        private val TAG = LoginActivity::class.java.name
    }
    private val viewModel: LoginViewModel by viewModel()

    override fun getVM(): LoginViewModel = viewModel

    override fun initViews() {
        showFrg(TAG, LoginFragment.TAG, false)
    }

    override fun getLayoutId(): Int = R.layout.activity_login
}