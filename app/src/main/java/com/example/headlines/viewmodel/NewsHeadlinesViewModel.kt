package com.example.headlines.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.headlines.network.Article
import com.example.headlines.network.NewsData


abstract class NewsHeadlinesViewModel : ViewModel() {

    sealed class State {
        data class Success(val result: NewsData) : State()
        object Loading : State()
        object Error : State()
    }

    abstract fun state(): LiveData<State>

    abstract fun fetchNewsHeadlines()

    abstract val clickedArticleLiveData: LiveData<Article>

    abstract fun setClickedArticle(article: Article)
}