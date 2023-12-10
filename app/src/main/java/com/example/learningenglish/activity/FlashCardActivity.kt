package com.example.learningenglish.activity

import android.widget.Toast
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.ActivityFlashCardBinding
import com.example.learningenglish.model.Word
import com.example.learningenglish.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlashCardActivity : BaseActivity<ActivityFlashCardBinding, MainViewModel>() {
    private lateinit var listWord: List<Word>
    private val sqlHelper: SQLHelper by inject()
    private var index = 0
    private var trueText = ""
    private var selectedText = ""
    companion object {
        private val TAG = MainActivity::class.java.name
    }
    private val viewModel: MainViewModel by viewModel()
    override fun getVM(): MainViewModel = viewModel

    override fun initViews() {
        listWord = sqlHelper.getAllWordTopic()
        binding.tvWord.text = listWord[index].name
        trueText = listWord[index].meaning
        setupClickListeners()
        binding.tvOk.setOnClickListener {
            if (trueText == selectedText) {
                val stateWord = listWord[index].learState++
                sqlHelper.updateWord(Word(listWord[index].id,listWord[index].name,listWord[index].meaning, stateWord))
                Toast.makeText(this@FlashCardActivity, "Chính xác", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@FlashCardActivity, "Chưa chính xác", Toast.LENGTH_SHORT).show()
            }
            index++
            reloadData()
        }
    }
    private fun reloadData() {
        binding.tvWord.text = listWord[index].name
    }
    private fun setupClickListeners() {
        binding.flipTextView1.setOnClickListener {
            selectedText = listWord[index].meaning.toString()
            binding.flipTextView1.getTextBackData(selectedText)
            binding.flipTextView1.flip()
        }
        binding.flipTextView2.setOnClickListener {
            selectedText = listWord[index].id.toString()
            binding.flipTextView1.getTextBackData(selectedText)
            binding.flipTextView2.flip()
        }

        binding.flipTextView3.setOnClickListener {
            selectedText = listWord[index].learState.toString()
            binding.flipTextView1.getTextBackData(selectedText)
            binding.flipTextView3.flip()
        }

        binding.flipTextView4.setOnClickListener {
            selectedText = listWord[index].name.toString()
            binding.flipTextView1.getTextBackData(selectedText)
            binding.flipTextView4.flip()
        }

    }
    override fun getLayoutId() = R.layout.activity_flash_card
}