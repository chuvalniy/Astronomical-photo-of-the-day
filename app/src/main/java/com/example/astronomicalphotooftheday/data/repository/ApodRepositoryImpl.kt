package com.example.astronomicalphotooftheday.data.repository

import com.example.astronomicalphotooftheday.core.utils.Resource
import com.example.astronomicalphotooftheday.data.local.ApodDao
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity
import com.example.astronomicalphotooftheday.data.remote.ApodApi
import com.example.astronomicalphotooftheday.data.remote.dto.ApodDto
import com.example.astronomicalphotooftheday.domain.repository.ApodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

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

    override suspend fun insertApod(apodEntity: ApodEntity) {
        return dao.insertApod(apodEntity)
    }

}