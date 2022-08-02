package com.example.gfu.workshop.unsplashed.photo.list.ui.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class TrendingPhotoSamples : PreviewParameterProvider<TrendingPhoto> {
    override val values: Sequence<TrendingPhoto>
        get() = sequenceOf(
            TrendingPhoto(
                name = "3-D Man",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ),
            TrendingPhoto(
                name = "Iron Man",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            )
        )
}