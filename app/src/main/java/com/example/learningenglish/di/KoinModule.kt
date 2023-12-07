package com.example.learningenglish.di

import android.content.Context
import android.location.LocationManager
import com.example.learningenglish.database.SQLHelper
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
        viewModel { SplashViewModel() }
        viewModel { LoginViewModel() }
        viewModel { FolderViewModel() }
        viewModel{ TopicViewModel()}
        viewModel { ProfileViewModel() }
        viewModel { EditProfileViewModel() }
        single { SQLHelper(androidContext()) }
        single { FirebaseAuth.getInstance() }
        single { Firebase.firestore }
        single { FirebaseStorage.getInstance().reference }
}