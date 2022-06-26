package com.example.feature_home.presentation.today_photo_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_home.domain.model.Photo
import com.example.feature_home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayPhotoViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _todayPhoto = MutableLiveData<Photo>()
    val todayPhoto: LiveData<Photo>
        get() = _todayPhoto

    private val _todayPhotoChannel = Channel<UiTodayPhotoEvent>()
    val todayPhotoEvent = _todayPhotoChannel.receiveAsFlow()

    init {
        getTodayPhoto()
    }

    private fun getTodayPhoto() {
        viewModelScope.launch {
            repository.getTodayPhoto().onEach { event ->
                when (event) {
                    is Resource.Loading -> {
                        _todayPhotoChannel.send(UiTodayPhotoEvent.ShowProgressBar(true))
                    }
                    is Resource.Success -> {
                        event.data?.let { photo ->
                            _todayPhoto.postValue(photo)
                            _todayPhotoChannel.send(UiTodayPhotoEvent.ShowProgressBar(false))
                        }
                    }
                    is Resource.Error -> {
                        _todayPhotoChannel.send(UiTodayPhotoEvent.ShowSnackbar("${event.message}"))
                    }
                }
            }.launchIn(this)
        }
    }


    sealed class UiTodayPhotoEvent {
        data class ShowProgressBar(val isLoading: Boolean) : UiTodayPhotoEvent()
        data class ShowSnackbar(val message: String) : UiTodayPhotoEvent()
    }
}