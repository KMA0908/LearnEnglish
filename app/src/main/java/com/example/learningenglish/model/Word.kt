package com.example.learningenglish.model

import java.io.Serializable

data class Word(val id: Int, val name: String,val meaning : String,val learState : String)  : Serializable