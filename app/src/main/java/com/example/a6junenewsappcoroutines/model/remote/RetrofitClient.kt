package com.example.a6junenewsappcoroutines.model.remote

import com.example.a6junenewsappcoroutines.model.config.KeyConfig
import com.example.a6junenewsappcoroutines.model.config.UrlConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
        fun getRetrofitService(): RetrofitService {
          /*val interceptor = Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.addHeader(KeyConfig.AUTHORIZATION,KeyConfig.KEY)
                return@Interceptor chain.proceed(builder.build())
            }*/

            val logInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .addInterceptor(AuthInterceptor())
                .build()

            return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(UrlConfig.BASE_URL)
                .build()
                .create(RetrofitService::class.java)
        }
}