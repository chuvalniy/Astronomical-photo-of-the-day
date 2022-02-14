package com.example.astronomicalphotooftheday.domain.repository

import com.example.astronomicalphotooftheday.core.utils.Resource
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity
import com.example.astronomicalphotooftheday.data.remote.dto.ApodDto

interface ApodRepository {

    suspend fun getTodayApod(): ApodDto

    suspend fun getRandomApods(number: String): List<ApodDto>

    suspend fun insertApod(apodEntity: ApodEntity)
}