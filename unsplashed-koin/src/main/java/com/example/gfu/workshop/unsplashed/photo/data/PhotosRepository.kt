package com.example.gfu.workshop.unsplashed.photo.data

import com.example.gfu.workshop.unsplashed.common.database.PhotosDatabase
import com.example.gfu.workshop.unsplashed.photo.data.api.PhotoApi
import com.example.gfu.workshop.unsplashed.photo.data.db.DatabasePhoto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PhotosRepository(
    private val database: PhotosDatabase,
    private val api: PhotoApi
) {
    private val scope = CoroutineScope(Dispatchers.IO)

    fun getPhotos(): Flow<List<DatabasePhoto>> {
        scope.launch { fetchData() }

        return database.photos().photos()
    }

    private suspend fun fetchData() {
        val response = api.getPhotos()
        database.photos().addPhotos(
            response.map {
                DatabasePhoto(
                    id = it.id,
                    description = it.description,
                    photoUrl = it.urls.small
                )
            }
        )
    }
}

