package com.example.feature_favorites.favorites_screen.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_photos_db")
data class PhotoEntity(
    @PrimaryKey
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val title: String,
    val url: String?
)
