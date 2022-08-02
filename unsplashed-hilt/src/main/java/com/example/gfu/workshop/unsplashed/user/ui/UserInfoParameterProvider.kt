package com.example.gfu.workshop.unsplashed.user.ui

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider

class UserInfoParameterProvider : CollectionPreviewParameterProvider<UserInfo>(listOf(Wasa))

private val Wasa = UserInfo(
    name = "Wasa Crispbread",
    profileImageUrl = "https://images.unsplash.com/profile-1655151625963-f0eec015f2a4image?dpr=1&auto=format&fit=crop&w=150&h=150&q=60&crop=faces&bg=fff",
    description = "Things we love:\n" +
            "\uD83C\uDF5E Crispbread (naturally) " +
            "\uD83C\uDF0D Our planet \uD83D\uDE0B " +
            "Delicious food, everyday",
    likes = 1337,
    photos = 200,
    followers = 6000
)