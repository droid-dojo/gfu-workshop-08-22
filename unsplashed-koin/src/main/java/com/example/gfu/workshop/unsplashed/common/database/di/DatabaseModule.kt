package com.example.gfu.workshop.unsplashed.common.database.di

import androidx.room.Room
import com.example.gfu.workshop.unsplashed.common.database.PhotosDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room
            .databaseBuilder(get(), PhotosDatabase::class.java, "photos.db")
            .build()
    }
}