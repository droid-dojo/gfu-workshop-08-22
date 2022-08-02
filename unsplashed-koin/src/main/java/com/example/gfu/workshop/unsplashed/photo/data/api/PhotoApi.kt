package com.example.gfu.workshop.unsplashed.photo.data.api

import com.example.gfu.workshop.unsplashed.photo.data.api.models.ApiPhoto
import retrofit2.http.GET

interface PhotoApi {
    @GET("photos/")
    suspend fun getPhotos(): List<ApiPhoto>
}