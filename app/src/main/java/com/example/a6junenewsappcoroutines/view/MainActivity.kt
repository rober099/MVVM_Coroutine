package com.example.a6junenewsappcoroutines.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a6junenewsappcoroutines.R
import com.example.a6junenewsappcoroutines.databinding.ActivityMainBinding
import com.example.a6junenewsappcoroutines.model.NewsRepository
import com.example.a6junenewsappcoroutines.view.adapter.NewsAdapter
import com.example.a6junenewsappcoroutines.viewmodel.NewsViewModel
import com.example.a6junenewsappcoroutines.viewmodel.factory.ViewModelFactory
import com.example.a6junenewsappcoroutines.model.dataclasses.Result
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var newsRepository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        newsViewModel.getNewsList()
        setupObserver()

    }
    private fun setupViewModel() {
        newsRepository = NewsRepository()
        viewModelFactory = ViewModelFactory(newsRepository)
        newsViewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

    private fun setupObserver() {
        newsViewModel.newsListResult.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    val restaurantItemAdapter = NewsAdapter(result.value)
                    binding.recyclerView.adapter = restaurantItemAdapter
                    binding.recyclerView.layoutManager = LinearLayoutManager(this)

                }

                is Result.Error -> {
                    Snackbar.make(binding.mainConstrainLayout, result.message.toString(), Snackbar.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }


}