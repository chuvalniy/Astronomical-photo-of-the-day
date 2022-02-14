package com.example.astronomicalphotooftheday.presentation.apod_today_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astronomicalphotooftheday.core.utils.Resource
import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.use_case.ApodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ApodTodayViewModel @Inject constructor(
    private val useCases: ApodUseCases
): ViewModel() {

    private val _apodItem = MutableStateFlow<Apod?>(null)
    val apodItem = _apodItem.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getTodayApod()
    }

    private fun getTodayApod() {
        useCases.getTodayApod().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _isLoading.value = true
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _apodItem.value = result.data
                }
                is Resource.Error -> {
                    _isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

}