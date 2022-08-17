package com.example.news.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.news.databinding.ActivityMainBinding
import com.example.news.model.datamodel.NewsModel
import com.example.news.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.init(binding, this)
        binding.viewModel = viewModel
        newsAdapter = NewsAdapter(emptyArray(), viewModel, this)
        rv_news.adapter = newsAdapter

        observeData()
    }

    private fun observeData() {
        val observer =
            Observer<Array<NewsModel>> { news -> newsAdapter.updateData(news) }
        viewModel.news?.observe(this, observer)
    }
}