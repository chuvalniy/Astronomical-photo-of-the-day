package com.example.feature_favorites.favorites_screen.presentation.favorites_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_favorites.favorites_screen.domain.model.Photo
import com.example.feature_favorites.favorites_screen.domain.use_case.DeletePhotoUseCase
import com.example.feature_favorites.favorites_screen.domain.use_case.GetPhotosUseCase
import com.example.feature_favorites.favorites_screen.domain.use_case.InsertPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val deletePhotoUseCase: DeletePhotoUseCase,
    private val getPhotosUseCase: GetPhotosUseCase,
    private val insertPhotoUseCase: InsertPhotoUseCase
) : ViewModel() {

    val photos = getPhotosUseCase().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    private val _favoritesChannel = Channel<UiFavoritesEvent>()
    val favoritesEvent = _favoritesChannel.receiveAsFlow()

    fun onEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.DeleteButtonClicked -> {
                viewModelScope.launch {
                    deletePhotoUseCase(event.photo)
                    _favoritesChannel.send(UiFavoritesEvent.ShowUndoDeletePhotoMessage(event.photo))
                }
            }
            is FavoritesEvent.DeletedPhotoRestored -> {
                viewModelScope.launch { insertPhotoUseCase(event.photo) }
            }
        }
    }

    sealed class UiFavoritesEvent {
        data class ShowUndoDeletePhotoMessage(val photo: Photo): UiFavoritesEvent()
    }
}