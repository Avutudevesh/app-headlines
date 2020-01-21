package com.example.headlines.network

import retrofit2.http.GET

interface NewsApiService {

    @GET
    suspend fun getNewsHeadlines(): List<String>
}