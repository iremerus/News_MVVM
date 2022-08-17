package com.example.news.model.datamodel

import com.google.gson.annotations.SerializedName

data class NewsModel (
    @SerializedName("name") val source: String,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val image: String,
    @SerializedName("publishedAt") val time: String,
    @SerializedName("content") val content: String
    )

data class SectionModel(
    @SerializedName("articles") val articles: Array<NewsModel>
)