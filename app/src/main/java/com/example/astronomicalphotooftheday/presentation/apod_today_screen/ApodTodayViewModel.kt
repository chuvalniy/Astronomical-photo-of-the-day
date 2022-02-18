package com.example.astronomicalphotooftheday.presentation.apod_today_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astronomicalphotooftheday.core.utils.Resource
import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.use_case.ApodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class ApodTodayViewModel @Inject constructor(
    private val useCases: ApodUseCases
): ViewModel() {

//    private val _apodItem = MutableStateFlow<Apod?>(null)
//    val apodItem = _apodItem.asStateFlow()
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading = _isLoading.asStateFlow()

    private val _uiEvent = MutableStateFlow<ApodTodayEvent>(ApodTodayEvent.Empty)
    val uiEvent = _uiEvent.asStateFlow()

    init {
        getTodayApod()
    }

    private fun getTodayApod() {
        viewModelScope.launch() {
            _uiEvent.value = ApodTodayEvent.Loading
            when (val apodResponse = useCases.getTodayApod()) {
                is Resource.Error -> _uiEvent.value = ApodTodayEvent.Failure(apodResponse.message!!)
                is Resource.Success -> _uiEvent.value = ApodTodayEvent.Success(apodResponse.data!!)
                else -> Unit
            }
        }
    }

    fun insertApods(apod: Apod) {
        viewModelScope.launch {
            useCases.insertApod(apod)
        }
    }

}