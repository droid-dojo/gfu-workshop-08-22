package com.example.gfu.workshop.unsplashed.user.ui

data class UserInfo(
    val name: String,
    val profileImageUrl: String?,
    val description: String?,
    val likes: Int,
    val photos: Int,
    val followers: Int,
)