package com.example.gfu.workshop.unsplashed.photo.list.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.gfu.workshop.unsplashed.photo.list.ui.model.TrendingPhoto
import com.example.gfu.workshop.unsplashed.photo.list.ui.model.TrendingPhotoSamples

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrendingPhotoCard(photo: TrendingPhoto) {

    val model = ImageRequest.Builder(LocalContext.current)
        .data(photo.imageUrl)
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()
    val painter = rememberAsyncImagePainter(model = model)

    Card(onClick = {}) {
        Column() {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = photo.name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun TrendingPhotoCardPreview(@PreviewParameter(TrendingPhotoSamples::class) photo: TrendingPhoto) {
    TrendingPhotoCard(photo)
}