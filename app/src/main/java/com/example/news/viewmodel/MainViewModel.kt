package com.example.news.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.example.news.databinding.ActivityMainBinding
import com.example.news.model.api.JobServices
import com.example.news.model.api.NewsApi
import com.example.news.model.datamodel.NewsModel
import com.example.news.model.datamodel.SectionModel
import com.example.news.view.Details
import com.example.news.view.MainActivity
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(): BaseViewModel() {
    var news: MutableLiveData<Array<NewsModel>>? = null
    var itemNews: MutableLiveData<NewsModel>? = null

    fun init(binding: ActivityMainBinding, activity: MainActivity) {
        setViewDataBinding(binding)
        getData()
    }

    private fun getData() {
        val newsApi = NewsApi.getRetroInstance().create(JobServices::class.java)
        news = MutableLiveData()
        GlobalScope.launch {
            val response = newsApi.getTurkeyTopNews()
            val newItems = response.body() as SectionModel
            news!!.postValue(newItems.articles)
        }
    }

    fun newsClicked(news: NewsModel, context: Context) {
        val intent = Intent(context, Details::class.java)
        intent.putExtra("news", Gson().toJson(news))
        context.startActivity(intent)
    }
}