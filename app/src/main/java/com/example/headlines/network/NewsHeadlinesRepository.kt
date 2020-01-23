package com.example.headlines.network

import androidx.lifecycle.LiveData

abstract class NewsHeadlinesRepository {
    abstract suspend fun fetchNewsHeadlines()

    abstract val articleListLiveData: LiveData<List<Article>>
}