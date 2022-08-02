package com.example.gfu.workshop.unsplashed.user.data.api.di

import com.example.gfu.workshop.unsplashed.user.data.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object UserApiModule {


    @Provides
    fun userApi(retrofit: Retrofit): UserApi = retrofit.create()
}