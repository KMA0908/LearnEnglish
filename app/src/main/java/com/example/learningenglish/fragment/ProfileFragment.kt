package com.example.learningenglish.fragment

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.learningenglish.R
import com.example.learningenglish.activity.EditProfileActivity
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentProfileBinding
import com.example.learningenglish.share.FragmentTransactionAnim

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    companion object {
        internal val TAG = ProfileFragment::class.java.name
    }
    override fun getVM(): ViewModel {
        TODO("Not yet implemented")
    }

    override fun initViews() {
       binding.tvLogout.setOnClickListener {
           val transAnim = FragmentTransactionAnim().apply {
               this.enter = R.anim.in_screen_right_to_left
               this.exit = R.anim.out_screen_right_to_left
               this.popEnter = R.anim.in_screen_left_to_right
               this.popExit = R.anim.out_screen_left_to_right
           }
           callBack?.showFrg(TAG, null, LoginFragment.TAG, true, transAnim)
       }
        binding.tbEditUser.setOnClickListener {
            startActivity(Intent(activity, EditProfileActivity::class.java))
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }
}
