package com.example.learningenglish.activity

import android.widget.Toast
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.ActivityMultipleChoiceBinding
import com.example.learningenglish.model.Word
import com.example.learningenglish.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MultipleChoiceActivity : BaseActivity<ActivityMultipleChoiceBinding, MainViewModel>() {
    private lateinit var listWord: List<Word>
    private val sqlHelper: SQLHelper by inject()
    private var index = 0

    companion object {
        private val TAG = MainActivity::class.java.name
    }
    private val viewModel: MainViewModel by viewModel()
    override fun getVM(): MainViewModel = viewModel

    override fun initViews() {
        listWord = sqlHelper.getAllWordTopic()
        binding.tvWord.text = listWord[index].name
        binding.tvWordA.text = listWord[index].meaning
        binding.tvWordB.text = ""+listWord[index].id
        binding.tvWordC.text = listWord[index].name
        binding.tvWordD.text = listWord[index].learState.toString()
        binding.tvWordA.setOnClickListener {
            var stateWord = 0
            if (listWord[index].learState < 3) {
                stateWord = ++listWord[index].learState
            }
            sqlHelper.updateWord(Word(listWord[index].id,listWord[index].name,listWord[index].meaning, stateWord))
            Toast.makeText(this@MultipleChoiceActivity, "Chính xác", Toast.LENGTH_SHORT).show()
            index++
            reloadData()
        }
        binding.tvWordB.setOnClickListener {
            var stateWord = 0
            if (listWord[index].learState < 3) {
                stateWord = ++listWord[index].learState
            }
            sqlHelper.updateWord(Word(listWord[index].id,listWord[index].name,listWord[index].meaning, stateWord))
            Toast.makeText(this@MultipleChoiceActivity, "Chưa chính xác", Toast.LENGTH_SHORT).show()
            index++
            reloadData()
        }
        binding.tvWordC.setOnClickListener {
            var stateWord = 0
            if (listWord[index].learState < 3) {
                stateWord = ++listWord[index].learState
            }
            sqlHelper.updateWord(Word(listWord[index].id,listWord[index].name,listWord[index].meaning, stateWord))
            Toast.makeText(this@MultipleChoiceActivity, "Chưa chính xác", Toast.LENGTH_SHORT).show()
            index++
            reloadData()
        }
        binding.tvWordD.setOnClickListener {
            var stateWord = 0
            if (listWord[index].learState < 3) {
                stateWord = ++listWord[index].learState
            }
            sqlHelper.updateWord(Word(listWord[index].id,listWord[index].name,listWord[index].meaning, stateWord))
            Toast.makeText(this@MultipleChoiceActivity, "Chưa chính xác", Toast.LENGTH_SHORT).show()
            index++
            reloadData()
        }
    }

    private fun reloadData() {
        if (index >= listWord.size) {
            Toast.makeText(this@MultipleChoiceActivity,"Đã hết từ vựng", Toast.LENGTH_SHORT).show()
        } else {
            binding.tvWord.text = listWord[index].name
            binding.tvWordA.text = listWord[index].meaning
            binding.tvWordB.text = ""+listWord[index].id
            binding.tvWordC.text = listWord[index].name
            binding.tvWordD.text = listWord[index].learState.toString()
        }
    }

    override fun getLayoutId() = R.layout.activity_multiple_choice
}