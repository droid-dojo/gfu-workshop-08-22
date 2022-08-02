package com.example.gfu.workshop.posts


sealed class ListScreenEvent {
    object LikeClicked : ListScreenEvent()

    data class UserClicked(val name: String) : ListScreenEvent()
}

