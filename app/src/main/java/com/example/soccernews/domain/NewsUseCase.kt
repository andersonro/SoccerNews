package com.example.soccernews.domain

import com.example.soccernews.core.UseCase
import com.example.soccernews.data.model.News
import com.example.soccernews.data.repositores.NewsRepository
import kotlinx.coroutines.flow.Flow

//class NewsUseCase(private val newsRepository: NewsRepository): UseCase.NoParam<List<News>>() {
class NewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun getListNews(): Flow<List<News>> {
        return newsRepository.getListNewsService()
    }

    /*
    override suspend fun execute(): Flow<List<News>> {
        return newsRepository.getListNewsService()
    }
    */

    /*
    suspend fun getNewsListFavorite(): Flow<List<News>>{
        return newsRepository.getListNewsFavorites()
    }

    suspend fun saveNews(news: News): Flow<Unit> {
        return flow {
            newsRepository.save(news)
            emit(Unit)
        }
    }
    */
}