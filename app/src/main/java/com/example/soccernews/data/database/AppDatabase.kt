package com.example.soccernews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.soccernews.data.database.dao.NewsDao
import com.example.soccernews.data.model.News

//@Database(entities = [News::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    /*
    abstract fun newsDao(): NewsDao

    companion object {
        fun getinstance(context: Context) : AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
        }
    }
    */
}