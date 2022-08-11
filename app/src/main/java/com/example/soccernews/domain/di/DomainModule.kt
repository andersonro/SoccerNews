package com.example.soccernews.domain.di

import android.util.Log
import com.example.soccernews.domain.NewsUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load(){
        loadKoinModules(useCaseModules())
    }

    private fun useCaseModules(): Module {
        return module {
            factory {
                Log.e("DomainModule", "DomainModule")
                NewsUseCase(get())
            }
        }
    }
}