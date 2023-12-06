package com.example.learningenglish.model

import java.io.Serializable

class Word : Serializable {
    var id = 0
    var name: String? = null
    var meaning: String? = null
    var learState : Int = 0
    // Constructor chính
    constructor(id: Int, name: String,meaning : String,learState : Int) {
        this.name = name
        this.meaning = meaning
        this.learState = learState
        this.id = id
    }
    constructor(name: String,meaning : String,learState : Int) {
        this.name = name
        this.meaning = meaning
        this.learState = learState
    }
}