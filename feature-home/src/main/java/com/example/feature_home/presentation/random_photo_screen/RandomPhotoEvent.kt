package com.example.feature_home.presentation.random_photo_screen

import com.example.feature_home.domain.model.Photo


sealed class RandomPhotoEvent {
    object ScreenSwipeRefresh : RandomPhotoEvent()
    data class AddButtonClicked(val photo: Photo) : RandomPhotoEvent()
}