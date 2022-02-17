package com.example.astronomicalphotooftheday.domain.use_case

import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity
import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.repository.ApodRepository

class DeleteApod(
    private val repository: ApodRepository
) {

    suspend operator fun invoke(apod: ApodEntity) {
        repository.deleteApod(apod)
    }
}