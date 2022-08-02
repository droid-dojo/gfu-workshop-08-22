package com.example.gfu.workshop.unsplashed.common.api.unsplashed

import com.example.gfu.workshop.unsplashed.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class UnsplashedApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val new = chain.request().newBuilder()
            .addHeader("Authorization", "Client-ID ${BuildConfig.API_KEY}")
            .build()
        return chain.proceed(new)
    }
}