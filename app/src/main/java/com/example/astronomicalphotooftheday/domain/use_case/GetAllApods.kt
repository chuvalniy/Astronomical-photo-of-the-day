package com.example.astronomicalphotooftheday.domain.use_case

import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.repository.ApodRepository

class GetAllApods(
    val repository: ApodRepository
) {

    suspend operator fun invoke(): List<Apod> {
        return repository.getAll().map { it.toApod() }
    }
}