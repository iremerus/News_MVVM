package com.example.news.model.api

import com.example.news.interfaces.Constants
import com.example.news.model.datamodel.NewsModel
import com.example.news.model.datamodel.SectionModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface JobServices {
    @GET(Constants.TURKEY_TOP)
    suspend fun getTurkeyTopNews(): Response<SectionModel>
}