package com.example.learningenglish.viewmodel

import com.example.learningenglish.base.BaseViewModel
import com.example.learningenglish.utils.SingleLiveEvent

class SplashViewModel : BaseViewModel() {
    val stateSplash =  SingleLiveEvent<Boolean>()
    init {
        stateSplash.value = true
    }
}