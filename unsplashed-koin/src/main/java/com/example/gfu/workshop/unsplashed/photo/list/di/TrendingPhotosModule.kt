package com.example.gfu.workshop.unsplashed.photo.list.di

import com.example.gfu.workshop.unsplashed.photo.data.PhotosRepository
import com.example.gfu.workshop.unsplashed.photo.list.TrendingPhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val trendingPhotosModule = module {
    singleOf(::PhotosRepository)
    viewModelOf(::TrendingPhotosViewModel)
}