package com.example.soccernews.data.services

import com.example.soccernews.data.model.News
import retrofit2.http.GET

interface NewsService {

    @GET("soccernews-api")
    suspend fun findListNews(): List<News>
}