package com.example.soccernews.data.repositores

import android.util.Log
import com.example.soccernews.data.database.AppDatabase
import com.example.soccernews.data.database.dao.NewsDao
import com.example.soccernews.data.model.News
import com.example.soccernews.data.services.NewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class NewsRepositoryImpl(private val service: NewsService,
                         private val appDataBase: AppDatabase): NewsRepository {

    private val dao = appDataBase.newsDao()

    override suspend fun getListNewsService() = flow {
        try {
            val newsList = service.findListNews()
            emit(newsList)
        }catch (e: HttpException){
            Log.e("SERVICE", e.message().toString())
        }
    }

    override suspend fun getListNewsFavorites() = flow {
        try {
            val newsList = dao.findFavorites()
            emit(newsList)
        }catch (e: Exception){
            Log.e("LOCAL", e.message!!)
        }
    }

    override suspend fun save(news: News) = flow {
        try {
            val n = dao.insertNews(news)
            emit(n)
        }catch (e: Exception){
            Log.e("SAVE", e.message!!)
        }
    }


}