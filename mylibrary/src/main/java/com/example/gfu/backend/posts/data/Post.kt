package com.example.gfu.backend.posts.data

import com.example.gfu.backend.User

data class Post(
    val author: User,
    val imageUrl: String
)
