package com.example.a6junenewsappcoroutines.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a6junenewsappcoroutines.R
import com.example.a6junenewsappcoroutines.databinding.ItemLayoutBinding
import com.example.a6junenewsappcoroutines.model.dataclasses.NewsData
import com.squareup.picasso.Picasso


class NewsAdapter(val newsData: List<NewsData>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var binding: ItemLayoutBinding

    override fun getItemCount() = newsData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(inflater, R.layout.item_layout, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsData[position])
    }

    inner class NewsViewHolder(var binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind( newsData:NewsData) {
            binding.news = newsData
            Picasso.get()
                .load(newsData.image)
                .fit()
                .into(binding.imageView)
            binding.txtTitle.text = newsData.title
            binding.txtAuthor.text = newsData.author
            binding.txtDescription.text = newsData.description
        }
    }

}