package com.example.feature_favorites.favorites_screen.domain.repository

import com.example.feature_favorites.favorites_screen.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getPhotos(): Flow<List<Photo>>

    suspend fun insertPhoto(photo: Photo)

    suspend fun deletePhoto(photo: Photo)
}