package com.example.gfu.workshop.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gfu.backend.User
import com.example.gfu.backend.posts.data.Post
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PostsViewModel() : ViewModel() {


    private val _uiState = MutableStateFlow(ListUiState())
    val uiState = _uiState.asStateFlow()

    val events = MutableSharedFlow<ListScreenEvent>()

    fun sendEvent(event: ListScreenEvent) {
        viewModelScope.launch {
            events.emit(event)
        }
    }

    init {
        viewModelScope.launch {
            events
                .filter { it == ListScreenEvent.LikeClicked }
                .collect {
                    Log.d("Foo", "Neues Like")
                }
        }

        viewModelScope.launch {
            events
                .filterIsInstance<ListScreenEvent.UserClicked>()
                .collect {
                    it.name
                    Log.d("Foo", "Neues Event von anderem Collector")
                }
        }

        loadInitialData()
    }

    private fun loadInitialData() = viewModelScope.launch {
        _uiState.update { it.copy(loading = true) }
        delay(5_000)
        _uiState.update {
            it.copy(
                loading = false, posts = listOf(
                    Post(
                        author = User(name = "Hans Wurscht", location = "Hier"),
                        imageUrl = "https://random-d.uk/api/1.jpg"
                    )
                )
            )
        }
    }

}

