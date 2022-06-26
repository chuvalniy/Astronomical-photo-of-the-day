package com.example.feature_favorites.favorites_screen.domain.use_case

import com.example.feature_favorites.favorites_screen.domain.model.Photo
import com.example.feature_favorites.favorites_screen.domain.repository.FavoritesRepository

class DeletePhotoUseCase(
    private val repository: FavoritesRepository
) {

    suspend operator fun invoke(photo: Photo) = repository.deletePhoto(photo)
}