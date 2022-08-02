package com.example.gfu.workshop.unsplashed.photo.data

import com.example.gfu.workshop.unsplashed.common.database.PhotosDatabase
import com.example.gfu.workshop.unsplashed.photo.data.api.PhotoApi
import com.example.gfu.workshop.unsplashed.photo.data.db.DatabasePhoto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepository @Inject constructor(
    private val database: PhotosDatabase,
    private val api: PhotoApi
) {
    private val scope = CoroutineScope(Dispatchers.IO)

    suspend fun count(): Int = 0

    fun sayHello(name: String): String = "Hey $name"

    fun getPhotos(): Flow<List<DatabasePhoto>> {
        scope.launch { fetchData() }

        return database.photos().photos()
    }

    suspend fun findPhotoById(id: String): DatabasePhoto? {
        return database.photos().findById(id)
    }

    private suspend fun fetchData() {
        val response = api.getPhotos()
        database.photos().addPhotos(
            response.map {
                DatabasePhoto(
                    id = it.id,
                    description = it.description,
                    photoUrl = it.urls.small,
                    authorId = it.user.username
                )
            }
        )
    }
}

