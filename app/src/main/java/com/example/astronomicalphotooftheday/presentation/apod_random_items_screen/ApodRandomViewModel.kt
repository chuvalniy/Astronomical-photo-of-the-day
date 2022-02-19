package com.example.astronomicalphotooftheday.presentation.apod_random_items_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astronomicalphotooftheday.core.utils.Resource
import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.use_case.ApodUseCases
import com.example.astronomicalphotooftheday.presentation.apod_today_screen.ApodTodayEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodRandomViewModel @Inject constructor(
    private val apodUseCases: ApodUseCases
): ViewModel() {

    private val _uiEvent = MutableStateFlow<ApodRandomEvent>(ApodRandomEvent.Empty)
    val uiEvent = _uiEvent.asStateFlow()

    fun insertApods(apod: Apod) {
        viewModelScope.launch {
            apodUseCases.insertApod(apod)
        }
    }

    init {
        getRandomApods()
    }

    fun getRandomApods() {
        viewModelScope.launch {
            _uiEvent.value = ApodRandomEvent.Loading
            when (val apodResponse = apodUseCases.getRandomApods()) {
                is Resource.Error -> _uiEvent.value = ApodRandomEvent.Failure(apodResponse.message!!)
                is Resource.Success -> _uiEvent.value = ApodRandomEvent.Success(apodResponse.data!!)
                else -> Unit
            }
        }
    }
}