package com.example.gfu.workshop.unsplashed.common.api.unsplashed.di

import com.example.gfu.workshop.unsplashed.common.api.unsplashed.UnsplashedApiKeyInterceptor
import com.example.gfu.workshop.unsplashed.photo.data.api.PhotoApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val unsplashedApiModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(UnsplashedApiKeyInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(get())
            .build()
    }

    single { Json { ignoreUnknownKeys = true } }

    single {
        val contentType = "application/json".toMediaType()
        get<Json>().asConverterFactory(contentType)
    }

    single {
        get<Retrofit>().create(PhotoApi::class.java)
    }
}