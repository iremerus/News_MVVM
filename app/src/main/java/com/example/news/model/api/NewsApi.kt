package com.example.news.model.api

import com.example.news.interfaces.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApi {
    companion object {
        private var retrofit: Retrofit?=null

        fun getRetroInstance(): Retrofit {
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            return retrofit!!
        }
    }

}