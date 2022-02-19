package com.example.astronomicalphotooftheday.data.remote

import com.example.astronomicalphotooftheday.core.utils.Constants.API_KEY
import com.example.astronomicalphotooftheday.data.remote.dto.ApodDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApodApi {
    @GET("planetary/apod?api_key=${API_KEY}")
    suspend fun getTodayApod(): ApodDto

    @GET("planetary/apod")
    suspend fun getRandomApods(
        @Query("count") count: String = "5",
        @Query("api_key") apiKey: String = API_KEY
    ): List<ApodDto>

}