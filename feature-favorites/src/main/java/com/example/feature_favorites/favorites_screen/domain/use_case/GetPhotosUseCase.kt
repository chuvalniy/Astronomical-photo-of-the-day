package com.example.feature_favorites.favorites_screen.domain.use_case

import com.example.feature_favorites.favorites_screen.domain.repository.FavoritesRepository

class GetPhotosUseCase(
    private val repository: FavoritesRepository
) {

    operator fun invoke() = repository.getPhotos()
}