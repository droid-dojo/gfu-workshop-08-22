package com.example.gfu.workshop.posts.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.gfu.backend.User
import com.example.gfu.backend.posts.data.Post
import com.example.gfu.workshop.ui.theme.MyApplicationTheme
import com.example.gfu.workshop.user.ui.UserDataRow

@Composable
fun PostCard(post: Post) {
    val liked = rememberSaveable { mutableStateOf(false) }
    PostCard(post = post, liked = liked.value, onLiked = { liked.value = !liked.value })
}

@Composable
fun PostCard(post: Post, liked: Boolean, onLiked: () -> Unit) {

    Card() {
        Column {
            UserDataRow(
                user = post.author,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )


            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f),
                model = "https://random-d.uk/api/3.jpg",
                contentDescription = null,
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.Crop,
                loading = {
                    Column() {
                        CircularProgressIndicator()
                        Text("Wird geladen")
                    }
                },
                error = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null )
                }
            )


            Row() {

                IconButton(onClick = onLiked) {
                    Icon(
                        imageVector =
                        if (liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Like",
                    )
                }
            }
        }
    }
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PostCardPreview() {
    MyApplicationTheme() {
        PostCard(
            Post(
                author = User(name = "Sample", location = "Somwhere"),
                imageUrl = ""
            )
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LikedPostCardPreview() {
    MyApplicationTheme() {
        PostCard(
            post = Post(
                author = User(name = "Sample", location = "Somwhere"),
                imageUrl = ""
            ),
            liked = true,
            onLiked = {}
        )
    }
}