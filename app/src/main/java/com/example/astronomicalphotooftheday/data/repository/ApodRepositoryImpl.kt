package com.example.astronomicalphotooftheday.data.repository

import androidx.lifecycle.LiveData
import com.example.astronomicalphotooftheday.data.local.ApodDao
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity
import com.example.astronomicalphotooftheday.data.remote.ApodApi
import com.example.astronomicalphotooftheday.data.remote.dto.ApodDto
import com.example.astronomicalphotooftheday.domain.repository.ApodRepository
import kotlinx.coroutines.flow.Flow

class ApodRepositoryImpl(
    private val api: ApodApi,
    private val dao: ApodDao
): ApodRepository {

    override suspend fun getTodayApod(): ApodDto {
        return api.getTodayApod()
    }

    override suspend fun getRandomApods(): List<ApodDto> {
        return api.getRandomApods()
    }

    override suspend fun insertApods(apods: List<ApodEntity>) {
        dao.insertApods(apods)
    }

    override fun getAll(): LiveData<List<ApodEntity>> {
        return dao.getAll()
    }

    override suspend fun deleteApod(apod: ApodEntity) {
        dao.deleteApod(apod)
    }

    override suspend fun insertApod(apod: ApodEntity) {
        dao.insertApod(apod)
    }
}