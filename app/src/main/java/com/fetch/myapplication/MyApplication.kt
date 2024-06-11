package com.fetch.myapplication

import android.app.Application
import com.fetch.myapplication.modules.networkModule
import com.fetch.myapplication.modules.repositoryModule
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule, repositoryModule)
        }
    }
}