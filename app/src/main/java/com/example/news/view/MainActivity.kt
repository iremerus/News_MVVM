package com.example.news.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.ActivityMainBinding
import com.example.news.model.datamodel.NewsModel
import com.example.news.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

private var page = 1

class MainActivity : AppCompatActivity() {
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        rv_news.layoutManager = layoutManager

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.init(binding, this)
        binding.viewModel = viewModel
        newsAdapter = NewsAdapter(emptyArray(), viewModel, this)
        rv_news.adapter = newsAdapter

        observeData()

        var total = 0
        var loading = false

        rv_news.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            /**
             * recyclerView: RecyclerView
             * dx: Int, change in x
             * dy: Int, change in y
             * when scrolled load more content
             */
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visible = layoutManager.childCount
                    val past = layoutManager.findFirstCompletelyVisibleItemPosition()
                    total = newsAdapter.itemCount
                    if ((visible + past > total) && !loading) {
                        loading = true
                        page += 1
                        viewModel.addData()
                    }
                }
                if (total < newsAdapter.itemCount){
                    loading = false
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun observeData() {
        val observer =
            Observer<Array<NewsModel>> { news -> newsAdapter.updateData(news) }
        viewModel.news?.observe(this, observer)
    }
}

fun getPage(): Int {
    return page
}