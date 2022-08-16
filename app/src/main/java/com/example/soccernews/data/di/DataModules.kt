package com.example.soccernews.data.di

import android.util.Log
import com.example.soccernews.data.database.AppDatabase
import com.example.soccernews.data.repositores.NewsRepository
import com.example.soccernews.data.repositores.NewsRepositoryImpl
import com.example.soccernews.data.services.NewsService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModules {

    private const val BASE_URL = "https://arodevsistemas.com.br/"
    private const val TAG_OKHTTP = "OkHTTP"

    fun load(){
        loadKoinModules(networkModule() + repositoryModule() + dataBaseModule())
    }

    private fun networkModule(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    //Log.e(TAG_OKHTTP, "networkModule: $it")
                }

                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<NewsService>(get(), get())
            }

        }
    }

    private fun repositoryModule(): Module {
        return module {
            single<NewsRepository> {
                NewsRepositoryImpl(get(), get())
            }
        }
    }

    private fun dataBaseModule(): Module {
        return module {
            single {
                AppDatabase.getinstance(androidApplication())
            }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient,
                                                 factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)
    }


}