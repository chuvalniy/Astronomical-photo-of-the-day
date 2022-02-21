package com.example.astronomicalphotooftheday.domain.use_case

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity
import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.repository.ApodRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*

class GetAllApods(
    private val repository: ApodRepository
) {

    operator fun invoke(): LiveData<List<ApodEntity>> {

        return repository.getAll()
    }
}