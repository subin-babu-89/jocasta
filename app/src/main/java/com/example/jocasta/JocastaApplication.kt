package com.example.jocasta

import android.app.Application
import timber.log.Timber

class JocastaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}