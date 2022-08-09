package com.example.a6junenewsappcoroutines.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a6junenewsappcoroutines.model.NewsRepository

class ViewModelFactory(private var newsRepository: NewsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NewsRepository::class.java).newInstance(newsRepository)
    }
}