package com.example.a6junenewsappcoroutines.model.remote


import com.example.a6junenewsappcoroutines.model.dataclasses.LatestNews
import com.example.a6junenewsappcoroutines.model.dataclasses.NewsData
import retrofit2.Response
import retrofit2.http.GET


interface RetrofitService {
    @GET("latest-news?")
    suspend fun getAllNews(): Response<LatestNews>
}