package com.example.astronomicalphotooftheday.presentation.apod_favorites_screen

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity
import com.example.astronomicalphotooftheday.domain.model.Apod
import com.example.astronomicalphotooftheday.domain.use_case.ApodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodFavoritesViewModel @Inject constructor(
    private val useCases: ApodUseCases
) : ViewModel() {

    val savedApods = useCases.getAllApods()

    fun deleteApod(apod: ApodEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.deleteApod(apod)
        }
    }
}