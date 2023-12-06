package com.example.learningenglish.activity

import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.databinding.ActivityLoginBinding
import com.example.learningenglish.databinding.FragmentUserAccountBinding
import com.example.learningenglish.fragment.LoginFragment
import com.example.learningenglish.fragment.UserAccountFragment
import com.example.learningenglish.viewmodel.LoginViewModel
import com.example.learningenglish.viewmodel.UserAccountViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserAccountActivity: BaseActivity<FragmentUserAccountBinding, UserAccountViewModel>() {
    companion object {
        private val TAG = UserAccountActivity::class.java.name
    }
    private val viewModel: UserAccountViewModel by viewModel()

    override fun getVM(): UserAccountViewModel = viewModel

    override fun initViews() {
        showFrg(TAG, UserAccountFragment.TAG, false)
    }

    override fun getLayoutId(): Int = R.layout.activity_user_account
}