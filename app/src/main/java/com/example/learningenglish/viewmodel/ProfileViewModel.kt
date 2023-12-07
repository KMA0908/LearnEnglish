package com.example.learningenglish.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.learningenglish.base.BaseViewModel
import com.example.learningenglish.model.User
import com.example.learningenglish.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : BaseViewModel() {
    private val firestore: FirebaseFirestore= Firebase.firestore
    private val auth: FirebaseAuth=FirebaseAuth.getInstance()

    private val _user=MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val user=_user.asStateFlow()

    init {
        getUser()
    }

    fun getUser(){
        viewModelScope.launch {
            _user.emit(Resource.Loading())
        }
        firestore.collection("user").document(auth.uid!!)
            .addSnapshotListener { value, error ->
                if (error!=null){
                    viewModelScope.launch {
                        _user.emit(Resource.Error(error.message.toString()))
                    }
                }else{
                    val user=value?.toObject(User::class.java)
                    user?.let {
                        viewModelScope.launch {
                            _user.emit(Resource.Success(user))
                        }
                    }
                }
            }
    }

    fun logOut(){
        auth.signOut()
    }


}