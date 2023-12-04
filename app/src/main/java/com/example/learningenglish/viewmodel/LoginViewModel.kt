package com.example.learningenglish.viewmodel

import com.example.learningenglish.base.BaseViewModel
import com.example.learningenglish.firebase.FirebaseAuthenticationManager

class LoginViewModel: BaseViewModel() {
    private val firebaseAuthManager = FirebaseAuthenticationManager

    fun signUp(email: String,
               password: String,
               onSuccess: (() -> Unit) = {},
               onFail: ((String) -> Unit) = {}) {
        firebaseAuthManager.signUpWithEmailAndPassword(email, password, onSuccess, onFail)
    }

    fun signIn(email: String,
               password: String,
               onSuccess: (() -> Unit) = {},
               onFail: ((String) -> Unit) = {}) {
        firebaseAuthManager.signInWithEmailAndPassword(email, password, onSuccess, onFail)
    }

    fun isUserEmailVerified() = firebaseAuthManager.isMailVerified()

    fun sendResetPassword(email: String, onSuccess: (() -> Unit) = {}, onFail: ((String) -> Unit) = {}) {
        firebaseAuthManager.sendResetMail(email, onSuccess, onFail)
    }
}