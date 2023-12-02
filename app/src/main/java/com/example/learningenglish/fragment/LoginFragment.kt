package com.example.learningenglish.fragment

import android.content.Intent
import android.text.TextUtils
import com.example.learningenglish.R
import com.example.learningenglish.activity.MainActivity
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentLoginBinding
import com.example.learningenglish.share.FragmentTransactionAnim
import com.example.learningenglish.utils.ViewUtils
import com.example.learningenglish.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment: BaseFragment<FragmentLoginBinding>(){
    companion object {
        internal val TAG = LoginFragment::class.java.name
    }
    private val viewModel: LoginViewModel by sharedViewModel()

    override fun getVM() = viewModel

    override fun initViews() {
        binding.tvSignUp.setOnClickListener {
            val transAnim = FragmentTransactionAnim().apply {
                this.enter = R.anim.in_screen_right_to_left
                this.exit = R.anim.out_screen_right_to_left
                this.popEnter = R.anim.in_screen_left_to_right
                this.popExit = R.anim.out_screen_left_to_right
            }
            callBack?.showFrg(TAG, null, SignUpFragment.TAG, true, transAnim)
        }
        binding.btLogin.setOnClickListener {
            val email = binding.etLoginAccount.text.toString()
            val password = binding.etLoginPassword.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                ViewUtils.showMessageDialog(requireContext(), parentFragmentManager, R.string.msg_empty_field_sign_up)
                return@setOnClickListener
            }

            ViewUtils.showBlur(parentFragmentManager)
            viewModel.signIn(email, password, onSuccess = {
                ViewUtils.dismissBlur()
                if (!viewModel.isUserEmailVerified()) {
                    ViewUtils.showMessageDialog(requireContext(), parentFragmentManager, R.string.msg_mail_not_verified)
                } else {
                    goToMainScreen()
                }
            }, onFail = {
                ViewUtils.dismissBlur()
                ViewUtils.showMessageDialog(parentFragmentManager, it)
            })
        }
        binding.tvForgetPassword.setOnClickListener {
            val transAnim = FragmentTransactionAnim().apply {
                this.enter = R.anim.in_screen_right_to_left
                this.exit = R.anim.out_screen_right_to_left
                this.popEnter = R.anim.in_screen_left_to_right
                this.popExit = R.anim.out_screen_left_to_right
            }
            callBack?.showFrg(TAG, null, ResetPasswordFragment.TAG, true, transAnim)
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    private fun goToMainScreen() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}