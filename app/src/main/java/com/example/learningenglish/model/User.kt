package com.example.learningenglish.model

data class User(
    val userName: String,
    val address: String,
    val birth: String,
    val imagePath: String="",
){
    constructor():this("","","","")
}