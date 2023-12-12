package com.example.learningenglish.viewmodel

import com.example.learningenglish.base.BaseViewModel
import com.example.learningenglish.model.Word
import com.example.learningenglish.utils.SingleLiveEvent
import java.util.ArrayList

class SplashViewModel : BaseViewModel() {
    val stateSplash =  SingleLiveEvent<Boolean>()
    val prevListWords: MutableList<Word> = mutableListOf()

    init {
        stateSplash.value = true
    }
}