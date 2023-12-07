package com.example.learningenglish.di

import android.content.Context
import android.location.LocationManager
import com.example.learningenglish.database.SQLHelper
import com.example.learningenglish.viewmodel.FolderViewModel
import com.example.learningenglish.viewmodel.LoginViewModel
import com.example.learningenglish.viewmodel.MainViewModel
import com.example.learningenglish.viewmodel.SplashViewModel
import com.example.learningenglish.viewmodel.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val KoinModule = module {

        factory { androidContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager }
        viewModel { MainViewModel() }
        viewModel { HomeViewModel() }
        viewModel { SplashViewModel() }
        viewModel { LoginViewModel() }
        viewModel { ProfileViewModel() }
        viewModel { UserAccountViewModel() }
        single { FirebaseAuth.getInstance() }
        single { Firebase.firestore }
        single { FirebaseStorage.getInstance().reference }

        viewModel { FolderViewModel() }
        single { SQLHelper(androidContext()) }
}