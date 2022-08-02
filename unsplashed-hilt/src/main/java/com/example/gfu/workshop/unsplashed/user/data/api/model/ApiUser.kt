package com.example.gfu.workshop.unsplashed.user.data.api.model


import com.example.gfu.workshop.unsplashed.photo.data.api.models.ProfileImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    val id: String,
    val username: String,
    val name: String,
    @SerialName("profile_image") val profileImage: ProfileImage,
    val bio: String? = null,
    val downloads: Int = 0,
    @SerialName("first_name") val firstName: String? = null,
    @SerialName("followed_by_user") val followedByUser: Boolean = false,
    @SerialName("followers_count") val followersCount: Int = 0,
    @SerialName("following_count") val followingCount: Int = 0,
    @SerialName("instagram_username") val instagramUsername: String? = null,
    @SerialName("last_name") val lastName: String? = null,
    val location: String? = null,
    @SerialName("portfolio_url") val portfolioUrl: String? = null,
    @SerialName("total_collections") val totalCollections: Int = 0,
    @SerialName("total_likes") val totalLikes: Int = 0,
    @SerialName("total_photos") val totalPhotos: Int = 0,
)