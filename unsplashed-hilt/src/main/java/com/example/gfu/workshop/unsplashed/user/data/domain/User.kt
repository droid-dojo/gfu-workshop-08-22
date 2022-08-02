package com.example.gfu.workshop.unsplashed.user.data.domain

import com.example.gfu.workshop.unsplashed.photo.data.domain.Photo

data class User(
    val id: String,
    val name: String,
    val profileImageUrl: String? = null,
    val photos: List<Photo> = emptyList(),
    val bio: String? = null,
    val totalLikes: Int,
    val followers: Int,
    val totalPhotos: Int,
)
