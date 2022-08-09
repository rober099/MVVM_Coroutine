package com.example.a6junenewsappcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6junenewsappcoroutines.model.NewsRepository
import com.example.a6junenewsappcoroutines.model.dataclasses.NewsData
import com.example.a6junenewsappcoroutines.model.dataclasses.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    val newsList = MutableLiveData<List<NewsData>>()
    val newsListResult = MutableLiveData<Result<List<NewsData>>>()
    val inProgress = MutableLiveData<Result<Boolean>>()
    val error = MutableLiveData<String>()


    fun getNewsList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsRepository.getAllNews()
                inProgress.postValue(Result.InProgress(true))
                if (response.isSuccessful) {
                    response.body()?.let { newsResponse ->
                        newsList.postValue(newsResponse.news)
                        newsListResult.postValue(Result.Success(newsResponse.news))
                    }
                } else {
                    error.postValue("Something went wrong, please try again.")
                    newsListResult.postValue(
                        Result.Error(
                            "Something went wrong, please try again.",
                            Exception("")
                        )
                    )
                }
            } catch (e: Exception) {
                inProgress.postValue(Result.InProgress(false))
                error.postValue(e.message.toString())
                newsListResult.postValue(Result.Error(e.message, e))
            }
            inProgress.postValue(Result.InProgress(false))
        }
    }
}