package com.example.learningenglish.activity


import android.widget.Toast
import com.example.learningenglish.R
import com.example.learningenglish.base.BaseActivity
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.databinding.ActivityFillWordBinding
import com.example.learningenglish.model.Word
import com.example.learningenglish.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FillInWordActivity : BaseActivity<ActivityFillWordBinding, MainViewModel>() {
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
        binding.tvOk.setOnClickListener {
            if (listWord[index].name.equals(binding.tvWordMean.text.toString()))
                Toast.makeText(this@FillInWordActivity, "Chính xác", Toast.LENGTH_SHORT)
            else Toast.makeText(this@FillInWordActivity, "Chưa chính xác", Toast.LENGTH_SHORT)
            index++
            reloadData()
        }
    }

    private fun reloadData() {
        binding.tvWord.text = listWord[index].name
    }

    override fun getLayoutId() = R.layout.activity_fill_word
}