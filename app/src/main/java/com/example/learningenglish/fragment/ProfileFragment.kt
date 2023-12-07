package com.example.learningenglish.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.learningenglish.R
import com.example.learningenglish.activity.EditProfileActivity
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.databinding.FragmentProfileBinding
import com.example.learningenglish.share.FragmentTransactionAnim
import com.example.learningenglish.utils.Resource
import com.example.learningenglish.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    companion object {
        internal val TAG = ProfileFragment::class.java.name
    }
    private val viewModel: ProfileViewModel by viewModel()

    override fun getVM() = viewModel
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
        binding.tvName.text = FirebaseAuth.getInstance().currentUser?.email.toString()

        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest {
                when (it) {

                    is Resource.Success -> {
                        Glide.with(requireView()).load(it.data!!.imagePath)
                            .error(ColorDrawable(Color.BLACK))
                            .into(binding.ivProfile)
                        binding.tvName.text = "${it.data.userName} "
                        binding.edAddress.text = "${it.data.address} "
                        binding.edBirth.text = "${it.data.birth} "

                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }
}
