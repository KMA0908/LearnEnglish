package com.example.learningenglish.callback

import com.example.learningenglish.share.FragmentTransactionAnim

interface OnActionCallBack {
    fun showFrg(backTag : String, tag : String, isBacked : Boolean)
    fun showFrg(backTag : String, data  : Any?, tag : String, isBacked : Boolean, anim: FragmentTransactionAnim?)

    fun closeApp()
}