package com.example.feature_favorites.favorites_screen.data.repository

import com.example.feature_favorites.favorites_screen.data.local.FavoritesDatabase
import com.example.feature_favorites.favorites_screen.data.mapper.toPhotoDomain
import com.example.feature_favorites.favorites_screen.data.mapper.toPhotoEntity
import com.example.feature_favorites.favorites_screen.domain.model.Photo
import com.example.feature_favorites.favorites_screen.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val db: FavoritesDatabase
) : FavoritesRepository {

    private val dao = db.dao

    override fun getPhotos() = dao.getPhotos().map { it.map { photos -> photos.toPhotoDomain() } }

    override suspend fun insertPhoto(photo: Photo) = dao.insertPhoto(photo.toPhotoEntity())

    override suspend fun deletePhoto(photo: Photo) = dao.deletePhoto(photo.toPhotoEntity())
}