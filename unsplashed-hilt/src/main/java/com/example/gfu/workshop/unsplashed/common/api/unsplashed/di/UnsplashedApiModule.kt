package com.example.gfu.workshop.unsplashed.common.api.unsplashed.di

import com.example.gfu.workshop.unsplashed.common.api.unsplashed.UnsplashedApiKeyInterceptor
import com.example.gfu.workshop.unsplashed.photo.data.api.PhotoApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create


//
//val unsplashedApiModule = module {
//
//    single {
//        OkHttpClient.Builder()
//            .addInterceptor(UnsplashedApiKeyInterceptor())
//            .build()
//    }
//
//    single {
//        Retrofit.Builder()
//            .client(get())
//            .baseUrl("https://api.unsplash.com/")
//            .addConverterFactory(get())
//            .build()
//    }
//
//    single { Json { ignoreUnknownKeys = true } }
//
//    single {
//        val contentType = "application/json".toMediaType()
//        get<Json>().asConverterFactory(contentType)
//    }
//
//    single {
//        get<Retrofit>().create(PhotoApi::class.java)
//    }
//}

@Module
@InstallIn(SingletonComponent::class)
object UnsplashedApiModule {

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(UnsplashedApiKeyInterceptor())
        .build()

    @Provides
    fun retrofit(okHttpClient: OkHttpClient, converter: Converter.Factory) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(converter)
        .build()


    @Provides
    fun serializer(json: Json): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return json.asConverterFactory(contentType)
    }

    @Provides
    fun json() = Json { ignoreUnknownKeys = true }

    @Provides
    fun photoApi(retrofit: Retrofit): PhotoApi = retrofit.create()

}
