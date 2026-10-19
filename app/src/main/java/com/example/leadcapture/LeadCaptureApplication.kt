package com.example.leadcapture

import android.app.Application
import com.example.leadcapture.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LeadCaptureApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LeadCaptureApplication)
            modules(appModule)
        }
    }
}