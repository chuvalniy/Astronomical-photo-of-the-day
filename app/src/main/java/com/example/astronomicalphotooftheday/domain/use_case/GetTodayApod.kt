package com.example.astronomicalphotooftheday.domain.use_case

import com.example.astronomicalphotooftheday.core.utils.Resource
import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.repository.ApodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetTodayApod(
    private val repository: ApodRepository
) {

    operator fun invoke(): Flow<Resource<Apod>> = flow {
        try {
            emit(Resource.Loading())
            val remoteData = repository.getTodayApod().toApod()
            emit(Resource.Success(remoteData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "HttpException"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "IOException"))
        }
    }
}