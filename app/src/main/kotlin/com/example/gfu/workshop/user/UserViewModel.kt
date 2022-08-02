package com.example.gfu.workshop.user

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gfu.workshop.user.data.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {


    private val userId: Int = savedStateHandle.get<Int>("userId")
        ?: throw IllegalArgumentException("no user id  given")

    private val repository = UserRepository.getInstance()

    //    private val _uiState = MutableStateFlow(UserUiState(loading = true, name = "$userId"))
    val uiState = repository.getUser(userId)
        .map {
            UserUiState(loading = false, name = it.name)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UserUiState(loading = true, name = "")
        )


//    init {
//
//
//        viewModelScope.launch {
//            repository.getUser(userId).collect { user ->
//                uiState.up
//                _uiState.update { it.copy(loading = false, name = user.name) }
//            }
//        }
//
//    }

}

data class UserUiState(
    val loading: Boolean,
    val name: String,
)