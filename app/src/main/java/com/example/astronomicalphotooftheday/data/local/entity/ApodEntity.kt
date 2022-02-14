package com.example.astronomicalphotooftheday.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astronomicalphotooftheday.domain.model.Apod

@Entity(tableName = "apod_db")
data class ApodEntity(
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val title: String,
    val url: String?,
    @PrimaryKey val id: Int? = null
)