package com.example.news.model.api

import com.example.news.interfaces.Constants
import com.example.news.model.datamodel.SectionModel
import com.example.news.view.getPage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface JobServices {
    @Headers("X-Api-Key: 666bbfe1298e4b769ab0105fb1a8e0c9")
    @GET(Constants.TURKEY_TOP)
    suspend fun getTurkeyTopNews(@Query("page") pageNumber: Int = getPage()): Response<SectionModel>
}