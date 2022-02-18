package com.example.astronomicalphotooftheday.presentation.apod_today_screen

import com.example.astronomicalphotooftheday.domain.model.Apod

sealed class ApodTodayEvent {
    class Success(val apod: Apod): ApodTodayEvent()
    class Failure(val errorText: String): ApodTodayEvent()
    object Loading : ApodTodayEvent()
    object Empty : ApodTodayEvent()
}
