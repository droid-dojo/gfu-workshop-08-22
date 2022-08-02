package com.example.gfu.workshop.unsplashed.photo.list.ui

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gfu.workshop.unsplashed.R
import com.example.gfu.workshop.unsplashed.photo.list.TrendingEvent
import com.example.gfu.workshop.unsplashed.photo.list.TrendingPhotosViewModel
import com.example.gfu.workshop.unsplashed.photo.list.TrendingSideEffect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrendingPhotosScreen(onNavigation: () -> Unit = {}) {
    val viewModel: TrendingPhotosViewModel = koinViewModel()
    val state = viewModel.uiState.collectAsState().value

    val context = LocalContext.current

    LaunchedEffect(viewModel, onNavigation, context) {
        viewModel.oneTimeActions.consumeAsFlow().collect {
            when (it) {
                is TrendingSideEffect.NavigateToUser -> onNavigation()
                is TrendingSideEffect.ShowToast -> Toast.makeText(
                    context,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    TrendingPhotosScreen(state = state, eventPublisher = {
        viewModel.setEvent(it)
    })
}

@Composable
fun TrendingPhotosScreen(state: TrendingPhotosUiState, eventPublisher: (TrendingEvent) -> Unit) {
    Scaffold(
        topBar = {
            TrendingPhotosAppBar()
        },

        ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.photos) {
                TrendingPhotoCard(
                    photo = it,
//                    onClick = {
//                    eventPublisher(TrendingEvent.UserSelected(it.name)
//                }
                )
            }
            if (state.loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TrendingPhotosAppBar() {

    var searchState by remember { mutableStateOf(SearchState.Collapsed) }

    AnimatedContent(targetState = searchState,
        transitionSpec = {
            when (targetState) {
                SearchState.Collapsed -> fadeIn() with slideOutHorizontally { it * 2 } + fadeOut()
                SearchState.Expanded -> slideInHorizontally { it } + fadeIn() with fadeOut()
            }.using(SizeTransform(clip = true))
        }
    ) {

        when (it) {
            SearchState.Collapsed -> TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(onClick = { searchState = SearchState.Expanded }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                }
            )
            SearchState.Expanded -> OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                trailingIcon = {
                    IconButton(onClick = { searchState = SearchState.Collapsed }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            )
        }
    }
}

private enum class SearchState {
    Collapsed, Expanded
}