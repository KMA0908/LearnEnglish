package com.example.learningenglish.activity

import android.widget.Toast
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.ActivityMultipleChoiceBinding
import com.example.learningenglish.model.Word
import com.example.learningenglish.utils.WordGenerator
import com.example.learningenglish.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MultipleChoiceActivity : BaseActivity<ActivityMultipleChoiceBinding, MainViewModel>() {
    private lateinit var listResult: MutableList<String>
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
        val listData= WordGenerator().generateRandomStrings() as ArrayList
        listData.add(listWord[index].meaning)
        listResult = listData.toMutableList()
        listResult.shuffle()
        binding.tvWordA.text = listResult[0]
        binding.tvWordB.text = listResult[1]
        binding.tvWordC.text = listResult[2]
        binding.tvWordD.text = listResult[3]
        binding.tvWordA.setOnClickListener {
            checkIsSuccessAns(binding.tvWordA.text.toString())
            index++
            reloadData()
        }
        binding.tvWordB.setOnClickListener {
            checkIsSuccessAns(binding.tvWordB.text.toString())
            index++
            reloadData()
        }
        binding.tvWordC.setOnClickListener {
            checkIsSuccessAns(binding.tvWordC.text.toString())
            index++
            reloadData()
        }
        binding.tvWordD.setOnClickListener {
            checkIsSuccessAns(binding.tvWordD.text.toString())
            index++
            reloadData()
        }
    }

    private fun checkIsSuccessAns(ansString : String) {
        var stateWord = 0
        if (listWord[index].meaning == ansString){
            if (listWord[index].learState < 3) {
                stateWord = ++listWord[index].learState
            }
            sqlHelper.updateWord(Word(listWord[index].id,listWord[index].name,listWord[index].meaning, stateWord))
            Toast.makeText(this@MultipleChoiceActivity, "Chính xác", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MultipleChoiceActivity, "Chưa chính xác", Toast.LENGTH_SHORT).show()
        }
    }

    private fun reloadData() {
        if (index >= listWord.size) {
            Toast.makeText(this@MultipleChoiceActivity,"Đã hết từ vựng", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            binding.tvWord.text = listWord[index].name
            binding.tvWordA.text = listResult[0]
            binding.tvWordB.text = listResult[1]
            binding.tvWordC.text = listResult[2]
            binding.tvWordD.text = listResult[3]
        }
    }

    override fun getLayoutId() = R.layout.activity_multiple_choice
}