package com.example.feature_home.data.repository

import com.example.core.utils.Resource
import com.example.feature_home.data.remote.HomeApi
import com.example.feature_home.domain.model.Photo
import com.example.feature_home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val api: HomeApi
) : HomeRepository {

    override fun getTodayPhoto(): Flow<Resource<Photo>> = flow {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(api.getTodayPhoto().toPhotoDomain()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }

    override fun getRandomPhotos(): Flow<Resource<List<Photo>>> = flow {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(api.getRandomPhotos().map { it.toPhotoDomain() }))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }
}