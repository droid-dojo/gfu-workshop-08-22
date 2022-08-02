package com.example.gfu.workshop.unsplashed.photo.data.api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiPhotoAuthor(
    val id: String,
    val username: String,

    @SerialName("profile_image")
    val profileImage: ProfileImage
)