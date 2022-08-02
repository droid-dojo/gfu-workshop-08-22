package com.example.gfu.workshop.unsplashed.user.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gfu.workshop.unsplashed.photo.data.domain.Photo
import com.example.gfu.workshop.unsplashed.user.data.UserRepository
import com.example.gfu.workshop.unsplashed.user.data.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import require
import javax.inject.Inject


@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository
) : ViewModel() {

    private val userId: String = savedStateHandle.require("userId")

    private val _state = MutableStateFlow(UserDetailUiState(userId = userId))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() {
        _state.update { it.copy(loading = true) }
        val user = userRepository.getUser(userId = userId)
        _state.update { uiState ->
            uiState.copy(
                loading = false,
                photos = user.photos.map { it.asUiModel() },
                userInfo = user.asUserInfo()
            )
        }
    }

    private fun Photo.asUiModel() = UserDetailUiState.UserPhoto(
        url = url,
        description = description
    )

    private fun User.asUserInfo() = UserInfo(
        name = name,
        profileImageUrl = profileImageUrl,
        description = bio,
        likes = totalLikes,
        photos = totalPhotos,
        followers = followers,
    )

}
