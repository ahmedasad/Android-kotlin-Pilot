package com.example.baseproject.base

import android.app.Application
import com.example.baseproject.utils.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        SharedPreferences.with(this)

        }
}