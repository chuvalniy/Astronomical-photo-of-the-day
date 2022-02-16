package com.example.astronomicalphotooftheday.data.repository

import com.example.astronomicalphotooftheday.data.local.ApodDao
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity
import com.example.astronomicalphotooftheday.data.remote.ApodApi
import com.example.astronomicalphotooftheday.data.remote.dto.ApodDto
import com.example.astronomicalphotooftheday.domain.repository.ApodRepository

class ApodRepositoryImpl(
    private val api: ApodApi,
    private val dao: ApodDao
): ApodRepository {

    override suspend fun getTodayApod(): ApodDto {
        return api.getTodayApod()
    }

    override suspend fun getRandomApods(number: String): List<ApodDto> {
        return api.getRandomApods(number)
    }

    override suspend fun insertApods(apods: List<ApodEntity>) {
        return dao.insertApods(apods)
    }

    override suspend fun getAll(): List<ApodEntity> {
        return dao.getAll()
    }

    override suspend fun insertApod(apodEntity: ApodEntity) {
        return dao.insertApod(apodEntity)
    }
}