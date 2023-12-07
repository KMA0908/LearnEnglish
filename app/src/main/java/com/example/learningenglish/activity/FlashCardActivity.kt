package com.example.learningenglish.activity

import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.databinding.ActivityFlashCardBinding
import com.example.learningenglish.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlashCardActivity : BaseActivity<ActivityFlashCardBinding, MainViewModel>() {
    companion object {
        private val TAG = MainActivity::class.java.name
    }
    private val viewModel: MainViewModel by viewModel()
    override fun getVM(): MainViewModel = viewModel

    override fun initViews() {
        setupClickListeners()
    }
    private fun setupClickListeners() {
        binding.flipTextView1.setOnClickListener {
            binding.flipTextView1.flip()
            binding.flipTextView2.flip()
        }
        binding.flipTextView2.setOnClickListener {
            binding.flipTextView2.flip()
            binding.flipTextView1.flip()
        }

        binding.flipTextView3.setOnClickListener {

            binding.flipTextView3.flip()
            binding.flipTextView4.flip()
        }

        binding.flipTextView4.setOnClickListener {
            binding.flipTextView4.flip()
            binding.flipTextView3.flip()
        }

    }
    override fun getLayoutId() = R.layout.activity_flash_card
}