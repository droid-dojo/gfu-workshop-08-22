package com.example.gfu.workshop.unsplashed.photo.data.api.models


import com.example.gfu.workshop.unsplashed.photo.data.domain.Photo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiPhoto(
    val id: String,
    val description: String? = null,

    @SerialName("liked_by_user")
    val likedByUser: Boolean,

    val likes: Int,

    val urls: PhotoUrls,
    val user: ApiPhotoAuthor,
) {
    @Serializable
    data class PhotoUrls(
        val full: String,
        val raw: String,
        val regular: String,
        val small: String,
        val thumb: String
    )

    fun asDomainModel() = Photo(url = urls.regular, description = description)
}