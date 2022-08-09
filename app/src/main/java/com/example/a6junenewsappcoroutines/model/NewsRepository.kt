package com.example.a6junenewsappcoroutines.model

import com.example.a6junenewsappcoroutines.model.remote.RetrofitClient
import com.example.a6junenewsappcoroutines.model.remote.RetrofitService

class NewsRepository {
    private val retrofitService: RetrofitService = RetrofitClient.getRetrofitService()

    suspend fun getAllNews() = retrofitService.getAllNews()
}