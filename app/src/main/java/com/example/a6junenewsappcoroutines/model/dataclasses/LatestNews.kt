package com.example.a6junenewsappcoroutines.model.dataclasses

import com.example.a6junenewsappcoroutines.model.dataclasses.NewsData

data class LatestNews(
    var status:String,
    val news: List<NewsData>
)
