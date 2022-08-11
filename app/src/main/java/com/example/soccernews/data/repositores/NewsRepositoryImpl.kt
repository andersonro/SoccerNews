package com.example.soccernews.data.repositores

import android.util.Log
import com.example.soccernews.data.database.AppDatabase
import com.example.soccernews.data.model.News
import com.example.soccernews.data.services.NewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class NewsRepositoryImpl(private val service: NewsService): NewsRepository {

    //private val dao = database.newsDao()

    override suspend fun getListNewsService() = flow {
        try {
            val newsList = service.findListNews()
            Log.e("IMPL", "--------------------")
            Log.e("IMPL", newsList.toString())
            emit(newsList)
        }catch (e: HttpException){
            Log.e("SERVICE", e.message().toString())
        }
    }

    /*
    override suspend fun getListNewsFavorites() = flow {
        try {
            val newsList = dao.findFavorites()
            emit(newsList)
        }catch (e: HttpException){
            Log.e("LOCAL", e.message().toString())
        }
    }

    override suspend fun save(news: News) {
        dao.insert(news)
    }
    */

}