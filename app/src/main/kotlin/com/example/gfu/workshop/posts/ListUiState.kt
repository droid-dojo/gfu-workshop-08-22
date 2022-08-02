package com.example.gfu.workshop.posts

import com.example.gfu.backend.posts.data.Post

data class ListUiState(
    val loading: Boolean = true,
    val posts: List<Post> = emptyList(),
    val error: String? = null
)


data class UiUser(
    val name: String,
)

data class ApiUser(
    val firstName: String,
    val lastName: String
)