package com.example.learningenglish.callback

interface OnActionCallBack {
    fun showFrg(backTag : String, tag : String, isBacked : Boolean)
    fun showFrg(backTag : String, data  : Any?, tag : String, isBacked : Boolean)
    fun closeApp()
}