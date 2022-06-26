package com.example.feature_favorites.favorites_screen.presentation.favorites_screen

import com.example.feature_favorites.favorites_screen.domain.model.Photo

sealed class FavoritesEvent {
    data class DeleteButtonClicked(val photo: Photo) : FavoritesEvent()
    data class DeletedPhotoRestored(val photo: Photo): FavoritesEvent()
}