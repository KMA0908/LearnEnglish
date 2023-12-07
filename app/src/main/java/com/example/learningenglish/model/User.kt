package com.example.learningenglish.model

data class User(
    val userName: String,
    val email: String,
    val passWord: String,
    val imagePath: String="",
){
    constructor():this("","","","")
}