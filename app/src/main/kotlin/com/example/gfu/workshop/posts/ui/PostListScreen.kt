package com.example.gfu.workshop.posts.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gfu.workshop.posts.ListScreenEvent
import com.example.gfu.workshop.posts.ListUiState
import com.example.gfu.workshop.posts.PostsViewModel

@Composable
fun PostListScreen(viewModel: PostsViewModel = viewModel(), navigateToHome: () -> Unit) {
    val state = viewModel.uiState.collectAsState().value

    if(state.loading) {
        AlertDialog(onDismissRequest = { /*TODO*/ }, text = { Text("Hallo") }, buttons = {})
    }

    Column() {
        Button(onClick = { navigateToHome() }) {
            Text("Nach hause")
        }

        PostListScreen(
            state = state,
            eventPublisher = { viewModel.sendEvent(it) }
        )
    }
}

@Composable
fun PostListScreen(state: ListUiState, eventPublisher: (ListScreenEvent) -> Unit) {
    LazyColumn {
        items(state.posts) {
            PostCard(post = it, liked = false, onLiked = {
                eventPublisher(ListScreenEvent.LikeClicked)
            })
        }

        if (state.loading) {
            item {
                CircularProgressIndicator()
            }
        }
    }
}

