package com.example.soccernews.domain

import com.example.soccernews.core.UseCase
import com.example.soccernews.data.model.News
import com.example.soccernews.data.repositores.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun getListNews(): Flow<List<News>> {
        return newsRepository.getListNewsService()
    }

    suspend fun getListNewsFavorite(): Flow<List<News>> {
        return newsRepository.getListNewsFavorites()
    }

    suspend fun insertFavorite(news: News): Flow<Long> {
        return newsRepository.save(news)
    }


}