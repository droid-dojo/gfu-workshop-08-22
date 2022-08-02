package com.example.gfu.workshop.unsplashed.photo.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gfu.workshop.unsplashed.photo.data.domain.Photo

@Entity
data class DatabasePhoto(
    @PrimaryKey val id: String,
    val description: String?,
    val photoUrl: String,
    val authorId: String = "undefined",
) {

    fun asDomainModel() = Photo(url = photoUrl, description = description)
}
