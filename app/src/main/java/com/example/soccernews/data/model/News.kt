package com.example.soccernews.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_news")
data class News (
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var title: String? = "",
    var description: String? = "",
    var image: String? = "",
    var url: String? = "",
    var favorite: Boolean = false
    )