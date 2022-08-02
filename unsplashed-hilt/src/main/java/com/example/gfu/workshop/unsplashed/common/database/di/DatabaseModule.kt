package com.example.gfu.workshop.unsplashed.common.database.di

import android.content.Context
import androidx.room.Room
import com.example.gfu.workshop.unsplashed.common.database.PhotosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room
            .databaseBuilder(context, PhotosDatabase::class.java, "photos.db")
            .build()


}
