package com.example.learningenglish.fragment

import android.text.TextUtils
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentSignUpBinding
import com.example.learningenglish.dialog.SimpleMessageDialog
import com.example.learningenglish.utils.ViewUtils
import com.example.learningenglish.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignUpFragment: BaseFragment<FragmentSignUpBinding>() {
    companion object {
        internal val TAG = SignUpFragment::class.java.name
    }
    private val viewModel: LoginViewModel by sharedViewModel()

    override fun getVM() = viewModel

    override fun initViews() {
        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.btSignUp.setOnClickListener {
            val email = binding.etSignUpAccount.text.toString()
            val password = binding.etSignUpPassword.text.toString()
            val rePassword = binding.etSignUpRePassword.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword)) {
                ViewUtils.showMessageDialog(requireContext(), parentFragmentManager, R.string.msg_empty_field_sign_up)
                return@setOnClickListener
            }
            if (!password.equals(rePassword)) {
                ViewUtils.showMessageDialog(requireContext(), parentFragmentManager, R.string.msg_mismatch_confirm_password)
                return@setOnClickListener
            }

            ViewUtils.showBlur(parentFragmentManager)
            viewModel.signUp(email, password, onSuccess = {
                ViewUtils.dismissBlur()
                ViewUtils.showMessageDialog(requireContext(), parentFragmentManager, R.string.msg_sign_up_successfully) {
                    parentFragmentManager.popBackStack()
                }
            }, onFail = {
                ViewUtils.dismissBlur()
                ViewUtils.showMessageDialog(parentFragmentManager, it)
            })
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_sign_up
}