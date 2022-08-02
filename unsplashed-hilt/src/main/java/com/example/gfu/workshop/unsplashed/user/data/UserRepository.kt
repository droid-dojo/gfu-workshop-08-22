package com.example.gfu.workshop.unsplashed.user.data

import com.example.gfu.workshop.unsplashed.user.data.api.UserApi
import com.example.gfu.workshop.unsplashed.user.data.domain.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val api: UserApi
) {
    suspend fun getUser(userId: String): User {
        val user = api.getUser(userId)
        val photos = api.getUserPhotos(userId)

        return User(
            id = user.id,
            name = user.name,
            profileImageUrl = user.profileImage.large,
            photos = photos.map { it.asDomainModel() },
            bio = user.bio,
            totalLikes = user.totalLikes,
            followers = user.followersCount,
            totalPhotos = user.totalPhotos
        )
    }

}
