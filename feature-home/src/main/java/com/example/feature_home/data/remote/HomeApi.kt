package com.example.feature_home.data.remote

import com.example.core.utils.Constants.API_KEY
import com.example.feature_home.data.remote.dto.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("planetary/apod?api_key=${API_KEY}")
    suspend fun getTodayPhoto(): PhotoDto

    @GET("planetary/apod")
    suspend fun getRandomPhotos(
        @Query("count") count: String = "5",
        @Query("api_key") apiKey: String = API_KEY
    ): List<PhotoDto>

}