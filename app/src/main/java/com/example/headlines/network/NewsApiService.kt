package com.example.headlines.network

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    //TODO: Move the api key into properties file and read from build config
    @GET("top-headlines")
    suspend fun getNewsHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "dfa776a1ac664d73b00055477a399071"
    ): NewsData
}