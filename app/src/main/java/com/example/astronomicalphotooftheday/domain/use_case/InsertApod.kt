package com.example.astronomicalphotooftheday.domain.use_case

import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.repository.ApodRepository

class InsertApod(
    val repository: ApodRepository
) {

    suspend operator fun invoke(apod: Apod) {
        repository.insertApod(apod.toApodEntity())
    }
}