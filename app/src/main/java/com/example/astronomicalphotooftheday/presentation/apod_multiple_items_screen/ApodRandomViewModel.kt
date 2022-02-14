package com.example.astronomicalphotooftheday.presentation.apod_multiple_items_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astronomicalphotooftheday.core.utils.Resource
import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.use_case.ApodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ApodRandomViewModel @Inject constructor(
    private val apodUseCases: ApodUseCases
): ViewModel() {

    private val _listApods = MutableStateFlow<List<Apod>?>(null)
    val listApods = _listApods.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        // 5 is a test value
        getRandomApods("5")
    }

    fun getRandomApods(number: String) {
        apodUseCases.getRandomApods(number).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _isLoading.value = true
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _listApods.value = result.data
                }
                is Resource.Error -> {
                    _isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}