package com.example.newsappassignment.data.network

import com.example.newsappassignment.data.models.TopStoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTimesApi {
    @GET("topstories/v2/home.json")
    suspend fun getTopStories(
        @Query("api-key") apiKey: String
    ): Response<TopStoriesResponse>
}
