package com.example.learningenglish.viewmodel

import com.example.learningenglish.base.BaseViewModel

class HomeViewModel : BaseViewModel() {
    lateinit var enableViewPager: () -> Unit
    var currentPageIndex: Int = 0

}