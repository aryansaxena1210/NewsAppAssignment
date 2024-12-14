package com.example.newsappassignment.data.network

import com.example.newsappassignment.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    val api: NYTimesApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NYTimesApi::class.java)
    }

}


