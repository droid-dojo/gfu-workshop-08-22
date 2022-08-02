package com.example.gfu.workshop.unsplashed.user.ui

data class UserDetailUiState(
    val userId: String,
    val loading: Boolean = false,
    val userInfo: UserInfo? = null,
    val photos: List<UserPhoto> = emptyList()
) {
    data class UserPhoto(val url: String, val description: String? = null)
}