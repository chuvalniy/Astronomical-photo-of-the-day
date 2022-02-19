package com.example.astronomicalphotooftheday.domain.repository

import androidx.lifecycle.LiveData
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity
import com.example.astronomicalphotooftheday.data.remote.dto.ApodDto
import kotlinx.coroutines.flow.Flow

interface ApodRepository {

    suspend fun getTodayApod(): ApodDto

    suspend fun getRandomApods(): List<ApodDto>

    suspend fun insertApods(apods: List<ApodEntity>)

    fun getAll(): LiveData<List<ApodEntity>>

    suspend fun deleteApod(apod: ApodEntity)

    suspend fun insertApod(apod: ApodEntity)
}