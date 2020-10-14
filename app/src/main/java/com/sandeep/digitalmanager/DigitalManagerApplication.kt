package com.sandeep.digitalmanager

import android.app.Application
import com.facebook.stetho.Stetho

class DigitalManagerApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }
}