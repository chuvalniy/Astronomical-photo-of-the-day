package com.example.feature_home.data.repository

import com.example.core.utils.Resource
import com.example.feature_home.domain.model.Photo
import com.example.feature_home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class FakeRepository : HomeRepository {

    private val photo = Photo(
        date = UUID.randomUUID().toString(),
        explanation = UUID.randomUUID().toString(),
        hdurl = UUID.randomUUID().toString(),
        title = UUID.randomUUID().toString(),
        url = UUID.randomUUID().toString()
    )
    private val photos = mutableListOf<Photo>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override fun getTodayPhoto(): Flow<Resource<Photo>>  = flow {
        if (shouldReturnNetworkError) {
            emit(Resource.Error(message = "error"))
        } else {
            emit(Resource.Success(photo))
        }
    }

    override fun getRandomPhotos(): Flow<Resource<List<Photo>>> = flow {
        if (shouldReturnNetworkError) {
            emit(Resource.Error(message = "error"))
        } else {
            emit(Resource.Success(photos))
        }
    }
}