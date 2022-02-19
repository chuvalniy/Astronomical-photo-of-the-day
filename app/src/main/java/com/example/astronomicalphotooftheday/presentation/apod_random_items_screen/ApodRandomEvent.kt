package com.example.astronomicalphotooftheday.presentation.apod_random_items_screen

import com.example.astronomicalphotooftheday.domain.model.Apod

sealed class ApodRandomEvent {
    class Success(val apods: List<Apod>): ApodRandomEvent()
    class Failure(val errorText: String): ApodRandomEvent()
    object Loading : ApodRandomEvent()
    object Empty : ApodRandomEvent()
}