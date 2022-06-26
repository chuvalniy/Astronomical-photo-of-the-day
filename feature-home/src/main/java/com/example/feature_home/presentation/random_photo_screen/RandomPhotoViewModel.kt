package com.example.feature_home.presentation.random_photo_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_home.domain.model.Photo
import com.example.feature_home.domain.repository.HomeRepository
import com.example.core.utils.Resource
import com.example.feature_favorites.favorites_screen.domain.use_case.InsertPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.lang.Error
import javax.inject.Inject

@HiltViewModel
class RandomPhotoViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val insertPhotoUseCase: InsertPhotoUseCase
) : ViewModel() {

    private val _randomPhotos = MutableLiveData<List<Photo>>()
    val randomPhotos: LiveData<List<Photo>>
        get() = _randomPhotos

    private val _randomPhotosChanel = Channel<UiRandomPhotoEvent>()
    val randomPhotosEvent = _randomPhotosChanel.receiveAsFlow()

    private fun getRandomPhotos() {
        viewModelScope.launch {
            repository.getRandomPhotos().onEach { event ->
                when (event) {
                    is Resource.Loading -> {
                        _randomPhotosChanel.send(UiRandomPhotoEvent.ShowProgressBar(true))
                    }
                    is Resource.Success -> {
                        event.data?.let { photos ->
                            _randomPhotos.postValue(photos)
                            _randomPhotosChanel.send(UiRandomPhotoEvent.ShowProgressBar(false))
                        }
                    }
                    is Resource.Error -> {
                        _randomPhotosChanel.send(UiRandomPhotoEvent.ShowSnackbar("${event.message}"))
                    }
                }
            }.launchIn(this)
        }
    }

    init {
        getRandomPhotos()
    }

    fun onEvent(event: RandomPhotoEvent) {
        when (event) {
            is RandomPhotoEvent.ScreenSwipeRefresh -> {
                getRandomPhotos()
                viewModelScope.launch {
                    _randomPhotosChanel.send(UiRandomPhotoEvent.RefreshScreen(false))
                }
            }
            is RandomPhotoEvent.AddButtonClicked -> {
                viewModelScope.launch {
                    insertPhotoUseCase(event.photo.toFavoritesPhoto()) // refactor
                    _randomPhotosChanel.send(UiRandomPhotoEvent.ShowSnackbar("Successfully added"))
                }
            }
        }
    }

    sealed class UiRandomPhotoEvent {
        data class ShowProgressBar(val isLoading: Boolean): UiRandomPhotoEvent()
        data class ShowSnackbar(val message: String): UiRandomPhotoEvent()
        data class RefreshScreen(val isRefreshing: Boolean): UiRandomPhotoEvent()
    }
}