package com.example.feature_favorites.favorites_screen.data.repository

import com.example.feature_favorites.favorites_screen.domain.model.Photo
import com.example.feature_favorites.favorites_screen.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : FavoritesRepository {

    private val photos = mutableListOf<Photo>()

    override fun getPhotos(): Flow<List<Photo>> {
        return flow { emit(photos) }
    }

    override suspend fun insertPhoto(photo: Photo) {
        photos.add(photo)
    }

    override suspend fun deletePhoto(photo: Photo) {
        photos.remove(photo)
    }
}