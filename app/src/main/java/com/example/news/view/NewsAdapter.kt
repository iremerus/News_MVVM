package com.example.news.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.NewsLayoutBinding
import com.example.news.model.datamodel.NewsModel
import com.example.news.viewmodel.MainViewModel

class NewsAdapter (private var news: Array<NewsModel>, private val viewModel: MainViewModel, private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class NewsViewHolder(_binding: NewsLayoutBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        private val binding: NewsLayoutBinding = _binding
        fun bind(news: NewsModel) {
            binding.newsTitle = news
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = NewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsViewHolder ->  {
                holder.bind(news[position])
            }
        }
        holder.itemView.setOnClickListener{ viewModel.newsClicked(news[position], context) }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun updateData(news: Array<NewsModel>) {
        this.news = news
        notifyDataSetChanged()
    }
}