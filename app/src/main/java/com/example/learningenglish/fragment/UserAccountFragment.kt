package com.example.learningenglish.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseFragment
import com.example.learningenglish.database.User
import com.example.learningenglish.databinding.FragmentUserAccountBinding
import com.example.learningenglish.utils.Resource
import com.example.learningenglish.viewmodel.UserAccountViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserAccountFragment: BaseFragment<FragmentUserAccountBinding>() {
    companion object {
        internal val TAG = UserAccountFragment::class.java.name
    }

    private val viewModel: UserAccountViewModel by sharedViewModel()

    override fun getVM() = viewModel

    private var imageUri : Uri?=null
    private lateinit var imageActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageActivityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            imageUri=it.data?.data
            Glide.with(this).load(imageUri).into(binding.imageUser)
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
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.updateInfo.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        binding.buttonSaveUserAccount.startAnimation()
                    }
                    is Resource.Success -> {
                        binding.buttonSaveUserAccount.revertAnimation()

                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }


        binding.buttonSaveUserAccount.setOnClickListener {
            binding.apply {
                val firstName=edFirstName.text.toString().trim()
                val lastName=edLastName.text.toString().trim()
                val email=edEmail.text.toString().trim()
                val user= User(firstName, lastName, email)
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
            Glide.with(this@UserAccountFragment).load(data.imagePath).error(ColorDrawable(Color.BLACK)).into(imageUser)
            edFirstName.setText(data.firstName)
            edLastName.setText(data.lastName)
            edEmail.setText(data.email)

        }
    }


    override fun getLayoutId():Int = R.layout.fragment_user_account

}
