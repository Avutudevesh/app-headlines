package com.example.headlines.network

abstract class NewsHeadlinesRepository {
    abstract suspend fun fetchNewsHeadlines(): NewsData
}