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

    suspend operator fun invoke(): Resource<Apod> {
        return try {
            val result = repository.getTodayApod().toApod()
            Resource.Success(data = result)
        } catch (e: HttpException) {
            Resource.Error(message = e.message ?: "An error occurred")
        } catch (e: IOException) {
            Resource.Error(message = e.message ?: "An error occurred")
        }
    }
}