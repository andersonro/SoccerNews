package com.example.soccernews.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News (
    val id: Long,
    val title: String,
    val description: String,
    val image: String = "",
    val url: String = "",
    val favorite: Boolean = false
    ): Parcelable