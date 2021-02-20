package com.example.jocasta

import android.app.Application
import timber.log.Timber

/**
 * Subsclasses the application class to initiliaze timber for logging purposes
 */
class JocastaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}