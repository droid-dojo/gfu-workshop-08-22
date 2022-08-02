package com.example.gfu.workshop.unsplashed

import android.app.Application
import com.example.gfu.workshop.unsplashed.common.api.unsplashed.di.unsplashedApiModule
import com.example.gfu.workshop.unsplashed.common.database.di.databaseModule
import com.example.gfu.workshop.unsplashed.photo.list.di.trendingPhotosModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UnsplashedApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@UnsplashedApplication)
            modules(unsplashedApiModule, databaseModule)
            modules(trendingPhotosModule)
        }
    }
}