package com.example.learningenglish.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.learningenglish.R
import com.example.learningenglish.activity.LoginActivity
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.callback.OnActionCallBack
import com.example.learningenglish.databinding.FragmentProfileBinding
import com.example.learningenglish.share.FragmentTransactionAnim
import com.example.learningenglish.utils.Resource
import com.example.learningenglish.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment: BaseFragment<FragmentProfileBinding>() {
    companion object {
        internal val TAG = ProfileFragment::class.java.name
    }

    private val viewModel: ProfileViewModel by sharedViewModel()

    override fun getVM() = viewModel


    override fun initViews() {

        binding.linearEditProfile.setOnClickListener {
            val transAnim = FragmentTransactionAnim().apply {
                this.enter = R.anim.in_screen_right_to_left
                this.exit = R.anim.out_screen_right_to_left
                this.popEnter = R.anim.in_screen_left_to_right
                this.popExit = R.anim.out_screen_left_to_right
            }
            callBack?.showFrg(TAG, null, UserAccountFragment.TAG, true, transAnim)
        }




        binding.linearLogOut.setOnClickListener {
            viewModel.logOut()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }


        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest {
                when (it) {

                    is Resource.Success -> {
                        Glide.with(requireView()).load(it.data!!.imagePath)
                            .error(ColorDrawable(Color.BLACK))
                            .into(binding.imageUser)
                        binding.tvUserName.text = "${it.data.firstName} ${it.data.lastName}"

                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }
    override fun getLayoutId():Int = R.layout.fragment_profile

}

