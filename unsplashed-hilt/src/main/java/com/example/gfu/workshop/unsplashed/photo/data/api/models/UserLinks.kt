package com.example.gfu.workshop.unsplashed.photo.data.api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLinks(
    @SerialName("html")
    val html: String,
    @SerialName("likes")
    val likes: String,
    @SerialName("photos")
    val photos: String,
    @SerialName("portfolio")
    val portfolio: String,
    @SerialName("self")
    val self: String
)