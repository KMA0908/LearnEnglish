package com.example.learningenglish.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.databinding.ActivityEditProfileBinding
import com.example.learningenglish.model.User
import com.example.learningenglish.utils.Resource
import com.example.learningenglish.viewmodel.EditProfileViewModel
import com.example.learningenglish.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileActivity : BaseActivity<ActivityEditProfileBinding, EditProfileViewModel>() {
    companion object {
        internal val TAG = EditProfileActivity::class.java.name
    }
    private val viewModel: EditProfileViewModel by viewModel()

    override fun getVM(): EditProfileViewModel = viewModel

    private var imageUri : Uri?=null
    private lateinit var imageActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageActivityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            imageUri=it.data?.data
            Glide.with(this).load(imageUri).into(binding.ivProfile)
        }
    }


    override fun initViews() {

        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest {
                when (it) {

                    is Resource.Success -> {
                        showUserInformation(it.data!!)
                    }
                    is Resource.Error -> {
                        Toast.makeText(this@EditProfileActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.updateInfo.collectLatest {
                when (it) {

                    is Resource.Success -> {
                        Toast.makeText(this@EditProfileActivity, "Update successfully", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        Toast.makeText(this@EditProfileActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }


        binding.tvDone.setOnClickListener {
            binding.apply {
                val userName=edUserName.text.toString().trim()
                val email=edEmail.text.toString().trim()
                val password=edPassword.text.toString().trim()
                val user= User(userName, email, password)
                viewModel.updateUser(user, imageUri)
            }
        }

        binding.imageEdit.setOnClickListener {
            val intent=Intent(Intent.ACTION_GET_CONTENT)
            intent.type="image/*"
            imageActivityResultLauncher.launch(intent)
        }
    }

    private fun showUserInformation(data: User) {
        binding.apply {
            Glide.with(this@EditProfileActivity).load(data.imagePath).error(ColorDrawable(Color.BLACK)).into(ivProfile)
            edUserName.setText(data.userName)
            edEmail.setText(data.email)
            edPassword.setText(data.passWord)

        }
    }


    override fun getLayoutId() = R.layout.activity_edit_profile
}