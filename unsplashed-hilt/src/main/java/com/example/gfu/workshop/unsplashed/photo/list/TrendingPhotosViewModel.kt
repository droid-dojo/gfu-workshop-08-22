package com.example.gfu.workshop.unsplashed.photo.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gfu.workshop.unsplashed.photo.data.PhotosRepository
import com.example.gfu.workshop.unsplashed.photo.data.db.DatabasePhoto
import com.example.gfu.workshop.unsplashed.photo.list.ui.TrendingPhotosUiState
import com.example.gfu.workshop.unsplashed.photo.list.ui.model.TrendingPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingPhotosViewModel @Inject constructor(
    private val repository: PhotosRepository
) : ViewModel() {


    private val photos = repository.getPhotos()

    val uiState = photos.map {
        TrendingPhotosUiState(
            loading = false,
            photos = it.map { databasePhoto -> databasePhoto.asUiModel() })
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        initialValue = TrendingPhotosUiState(loading = true, photos = emptyList())
    )

    private val _sideEffects = Channel<TrendingSideEffect>()
    val oneTimeActions = _sideEffects.receiveAsFlow()
    val events = MutableSharedFlow<TrendingEvent>()

    fun setEvent(event: TrendingEvent) {
        viewModelScope.launch {
            events.emit(event)
        }
    }

    init {
        listenToEvents()
    }

    private fun listenToEvents() = viewModelScope.launch {
        events.collect() {
            when (it) {
                is TrendingEvent.PhotoClicked -> {

                    val photo = repository.findPhotoById(it.photo.id)
                    if (photo != null) {
                        _sideEffects.send(
                            TrendingSideEffect.NavigateToUser(
                                photo.authorId
                            )
                        )
                    } else {
                        _sideEffects.send(TrendingSideEffect.ShowToast("Fehler bild nicht gefunden"))
                    }
                }
            }
        }
    }


    private fun DatabasePhoto.asUiModel() = TrendingPhoto(
        name = description.orEmpty(),
        imageUrl = photoUrl,
        id = id,
    )

}

sealed class TrendingEvent() {
    data class PhotoClicked(val photo: TrendingPhoto) : TrendingEvent()
}

sealed class TrendingSideEffect() {
    data class ShowToast(val message: String) : TrendingSideEffect()
    data class NavigateToUser(val userId: String) : TrendingSideEffect()
}

