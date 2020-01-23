package com.example.headlines.network

import androidx.lifecycle.LiveData
import com.example.headlines.db.HeadlinesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsHeadlinesRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val headlinesDatabase: HeadlinesDatabase
) : NewsHeadlinesRepository() {

    override val articleListLiveData: LiveData<List<Article>> =
        headlinesDatabase.headlinesDao().loadArticles()

    override suspend fun fetchNewsHeadlines() {
        withContext(Dispatchers.IO) {
            val articles = newsApiService.getNewsHeadlines().articles
            headlinesDatabase.headlinesDao().saveArticles(articles)
        }
    }

}