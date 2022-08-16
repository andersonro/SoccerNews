package com.example.soccernews.presentation.di

import android.util.Log
import com.example.soccernews.presentation.FavoriteViewModel
import com.example.soccernews.presentation.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModules())
    }

    private fun viewModelModules(): Module {

        return module {
            viewModel { NewsViewModel(get()) }
            viewModel { FavoriteViewModel(get()) }
        }
    }

}