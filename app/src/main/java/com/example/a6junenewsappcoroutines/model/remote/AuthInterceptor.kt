package com.example.a6junenewsappcoroutines.model.remote

import com.example.a6junenewsappcoroutines.model.config.KeyConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()
        currentRequest.addHeader("Authorization",KeyConfig.KEY)

        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)
    }
}