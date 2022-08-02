package com.example.gfu.workshop.unsplashed.photo.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gfu.workshop.unsplashed.photo.data.PhotosRepository
import com.example.gfu.workshop.unsplashed.photo.data.db.DatabasePhoto
import com.example.gfu.workshop.unsplashed.photo.list.ui.TrendingPhotosUiState
import com.example.gfu.workshop.unsplashed.photo.list.ui.model.TrendingPhoto
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TrendingPhotosViewModel(
    private val repository: PhotosRepository
) : ViewModel() {


    val uiState = repository.getPhotos().map {
        TrendingPhotosUiState(
            loading = false,
            photos = it.map { databasePhoto -> databasePhoto.asUiModel() })
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        initialValue = TrendingPhotosUiState(loading = true, photos = emptyList())
    )

    val oneTimeActions = Channel<TrendingSideEffect>()
    val events = MutableSharedFlow<TrendingEvent>()

    fun setEvent(event: TrendingEvent) {
        viewModelScope.launch {
            events.emit(event)
        }
    }

    init {

        viewModelScope.launch {
            delay(5_000)
            oneTimeActions.send(TrendingSideEffect.ShowToast("Hallo vom ViewModel"))
            delay(5_000)
            oneTimeActions.send(TrendingSideEffect.ShowToast("Hallo again"))
        }
    }


    private fun DatabasePhoto.asUiModel() = TrendingPhoto(
        name = description.orEmpty(),
        imageUrl = photoUrl
    )

}

sealed class TrendingEvent() {
    data class UserSelected(val user: String) : TrendingEvent()
}

sealed class TrendingSideEffect() {
    data class ShowToast(val message: String) : TrendingSideEffect()
    data class NavigateToUser(val userId: String) : TrendingSideEffect()
}

