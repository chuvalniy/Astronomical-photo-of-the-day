package com.example.astronomicalphotooftheday.domain.use_case

import com.example.astronomicalphotooftheday.core.utils.Resource
import com.example.astronomicalphotooftheday.data.repository.ApodRepositoryImpl
import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.repository.ApodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetRandomApods(
    private val repository: ApodRepository
) {

    suspend operator fun invoke(number: String): Resource<List<Apod>> {
        return try {
            val remoteData = repository.getRandomApods(number).map { it.toApod() }
            Resource.Success(data = remoteData)
        } catch (e: HttpException) {
            Resource.Error(message = e.message ?: "An error occurred")
        } catch (e: IOException) {
            Resource.Error(message = e.message ?: "An error occurred")
        }
    }
}