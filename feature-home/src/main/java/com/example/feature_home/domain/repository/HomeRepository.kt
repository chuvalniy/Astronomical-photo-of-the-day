package com.example.feature_home.domain.repository

import com.example.feature_home.domain.model.Photo
import com.example.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getTodayPhoto(): Flow<Resource<Photo>>

    fun getRandomPhotos(): Flow<Resource<List<Photo>>>

}