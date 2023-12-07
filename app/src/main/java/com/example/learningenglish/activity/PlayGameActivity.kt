package com.example.learningenglish.activity

import android.content.Intent
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.databinding.ActivityPlayGameBinding
import com.example.learningenglish.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayGameActivity : BaseActivity<ActivityPlayGameBinding, MainViewModel>() {
    companion object {
        private val TAG = MainActivity::class.java.name
    }
    private val viewModel: MainViewModel by viewModel()
    override fun getVM(): MainViewModel = viewModel

    override fun initViews() {
        binding.tvFlashCard.setOnClickListener {
            startActivity(Intent(this@PlayGameActivity, FlashCardActivity::class.java))
        }
        binding.tvMultipleChoice.setOnClickListener {
            startActivity(Intent(this@PlayGameActivity, MultipleChoiceActivity::class.java))
        }
        binding.tvFillInWords.setOnClickListener {
            startActivity(Intent(this@PlayGameActivity, FillInWordActivity::class.java))
        }
    }

    override fun getLayoutId() = R.layout.activity_play_game
}