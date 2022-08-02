package com.example.gfu.workshop.user.ui

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.gfu.workshop.user.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel) {
    val state = viewModel.uiState.collectAsState().value

    if (state.loading) {
        CircularProgressIndicator()
    }

    Text(text = state.name)

}