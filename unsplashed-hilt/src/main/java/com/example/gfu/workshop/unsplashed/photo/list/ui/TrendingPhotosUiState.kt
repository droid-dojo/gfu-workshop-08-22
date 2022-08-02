package com.example.gfu.workshop.unsplashed.photo.list.ui

import com.example.gfu.workshop.unsplashed.photo.list.ui.model.TrendingPhoto

data class TrendingPhotosUiState(
    val loading: Boolean,
    val photos: List<TrendingPhoto>
)