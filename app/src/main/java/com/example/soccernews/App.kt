package com.example.soccernews

import android.app.Application
import com.example.soccernews.data.di.DataModules
import com.example.soccernews.domain.di.DomainModule
import com.example.soccernews.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(androidContext = this@App)
        }

        DataModules.load()
        DomainModule.load()
        PresentationModule.load()

    }
}