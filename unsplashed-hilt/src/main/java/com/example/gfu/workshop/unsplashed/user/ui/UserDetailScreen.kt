package com.example.gfu.workshop.unsplashed.user.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.gfu.workshop.unsplashed.ui.theme.MyApplicationTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun UserDetailScreen() {
    val viewModel: UserDetailViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value

    UserDetailScreen(state = state)

}


@Composable
fun UserDetailScreen(state: UserDetailUiState) {
    Scaffold(
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }, title = {
                Text(text = state.userId)
            })
        }
    ) { scaffoldPadding ->

        LazyVerticalGrid(
            modifier = Modifier.padding(scaffoldPadding),
            columns = GridCells.Fixed(count = 3),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            contentPadding = PaddingValues(top = 16.dp)
        ) {

            if (state.userInfo != null) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    UserInfoSection(
                        info = state.userInfo,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(modifier = Modifier.size(32.dp))
                }
            }

            if (state.photos.isEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {

                }
            }

            items(state.photos) {
                UserPhoto(it)
            }
        }

    }
}

@Composable
fun UserPhoto(photo: UserDetailUiState.UserPhoto) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photo.url)
            .size(Size.ORIGINAL)
            .build()
    )


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)

    ) {

        Image(
            modifier = Modifier
                .fillMaxSize()
                .placeholder(
                    visible = painter.state is AsyncImagePainter.State.Loading,
                    highlight = PlaceholderHighlight.shimmer()
                ),
            painter = painter,
            contentDescription = photo.description,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )

        if (painter.state == AsyncImagePainter.State.Empty || painter.state is AsyncImagePainter.State.Error) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = Icons.Default.Email,
                contentDescription = null
            )
        }
    }

}


@Composable
fun UserInfoSection(info: UserInfo, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(url = info.profileImageUrl, size = 80.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AggregatedStatistic(title = "Likes", statistic = "${info.likes}")
                AggregatedStatistic(title = "Photos", statistic = "${info.photos}")
                AggregatedStatistic(title = "Followers", statistic = "${info.followers}")
            }
        }

        Text(text = info.name, style = MaterialTheme.typography.h5)

        if (!info.description.isNullOrEmpty()) {
            Text(
                text = info.description,
                style = MaterialTheme.typography.caption
            )
        }
    }

}

@Composable
fun AggregatedStatistic(title: String, statistic: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = statistic, style = MaterialTheme.typography.h6)
        Text(text = title, style = MaterialTheme.typography.caption)
    }
}

@Composable
fun ProfileImage(url: String?, size: Dp = 48.dp) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build()
    )

    Image(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .placeholder(visible = painter.state is AsyncImagePainter.State.Loading)
            .border(width = 2.dp, color = MaterialTheme.colors.primary, shape = CircleShape),
        painter = painter,
        contentDescription = null,
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun UserDetailScreenPreview(@PreviewParameter(UserInfoParameterProvider::class) user: UserInfo) {

    MyApplicationTheme() {
        UserDetailScreen(
            state = UserDetailUiState(
                userId = "Sample",
                userInfo = user,
            )
        )
    }

}