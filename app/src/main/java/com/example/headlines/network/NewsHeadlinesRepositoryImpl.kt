package com.example.headlines.network

import javax.inject.Inject

class NewsHeadlinesRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsHeadlinesRepository() {

    override suspend fun fetchNewsHeadlines(): NewsData =
        newsApiService.getNewsHeadlines()

}