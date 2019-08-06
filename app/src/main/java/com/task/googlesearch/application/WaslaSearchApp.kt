package com.task.googlesearch.application

import android.app.Application
import com.task.googlesearch.core.data.remote.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WaslaSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WaslaSearchApp)
            modules(remoteModule)
        }
    }
}