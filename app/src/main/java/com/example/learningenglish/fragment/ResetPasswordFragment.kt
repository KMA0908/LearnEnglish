package com.example.learningenglish.fragment

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentResetPasswordBinding
import com.example.learningenglish.utils.ViewUtils
import com.example.learningenglish.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ResetPasswordFragment: BaseFragment<FragmentResetPasswordBinding>() {
    companion object {
        internal val TAG = ResetPasswordFragment::class.java.name
    }
    private val viewModel: LoginViewModel by sharedViewModel()

    override fun getVM(): ViewModel = viewModel

    override fun initViews() {
        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.btSendResetMail.setOnClickListener {
            val email = binding.etEmailReset.text.toString()

            if (TextUtils.isEmpty(email)) {
                ViewUtils.showMessageDialog(requireContext(), parentFragmentManager, R.string.msg_empty_field_sign_up)
                return@setOnClickListener
            }
            ViewUtils.showBlur(parentFragmentManager)
            viewModel.sendResetPassword(email, onSuccess = {
                ViewUtils.dismissBlur()
                ViewUtils.showMessageDialog(requireContext(), parentFragmentManager, R.string.msg_sent_reset_mail)
                parentFragmentManager.popBackStack()
            }, onFail = {
                ViewUtils.dismissBlur()
                ViewUtils.showMessageDialog(parentFragmentManager, it)
            })
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_reset_password
}