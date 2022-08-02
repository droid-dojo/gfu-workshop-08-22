package com.example.gfu.workshop.unsplashed.user.data.api

import com.example.gfu.workshop.unsplashed.photo.data.api.models.ApiPhoto
import com.example.gfu.workshop.unsplashed.user.data.api.model.ApiUser
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("users/{userName}")
    suspend fun getUser(@Path("userName") userName: String) : ApiUser

    @GET("users/{userName}/photos")
    suspend fun getUserPhotos(@Path("userName") userName: String) : List<ApiPhoto>

}