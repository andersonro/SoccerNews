package com.example.soccernews.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soccernews.data.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM tb_news WHERE favorite = 1")
    fun findFavorites(): List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News)
}