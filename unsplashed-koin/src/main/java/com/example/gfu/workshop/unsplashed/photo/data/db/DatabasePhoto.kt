package com.example.gfu.workshop.unsplashed.photo.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabasePhoto(
    @PrimaryKey val id: String,

    val description: String?,
    val photoUrl: String,
)
