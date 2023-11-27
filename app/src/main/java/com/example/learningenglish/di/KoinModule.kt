package com.example.learningenglish.di

import android.content.Context
import android.location.LocationManager
import com.example.learningenglish.database.AppDatabase
import com.example.learningenglish.viewmodel.MainViewModel
import com.example.learningenglish.viewmodel.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val KoinModule = module {
        single { AppDatabase.invoke(androidContext()) }
        factory { androidContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager }
        viewModel { MainViewModel() }
        viewModel { SplashViewModel() }
}