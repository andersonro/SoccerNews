package com.example.soccernews.data.repositores

import com.example.soccernews.data.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getListNewsService(): Flow<List<News>>
    suspend fun getListNewsFavorites(): Flow<List<News>>
    suspend fun save(news: News): Flow<Long>
}