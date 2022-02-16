package com.example.astronomicalphotooftheday.presentation.apod_favorites_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.use_case.ApodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodFavoritesViewModel @Inject constructor(
    private val useCases: ApodUseCases
): ViewModel() {

    private val _savedApods = MutableStateFlow<List<Apod>?>(null)
    val savedApods get() = _savedApods.asStateFlow()

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            _savedApods.value = useCases.getAllApods()
        }
    }
}