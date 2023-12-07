package com.example.learningenglish.database

data class User(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val imagePath: String="",
){
    constructor():this("","","","")
}
