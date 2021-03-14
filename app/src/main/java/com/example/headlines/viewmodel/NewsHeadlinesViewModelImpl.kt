package com.example.headlines.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.headlines.network.Article
import com.example.headlines.network.NewsHeadlinesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsHeadlinesViewModelImpl @Inject constructor(
    private val newsHeadlinesRepository: NewsHeadlinesRepository,
    private val coroutineScope: CoroutineScope
) : NewsHeadlinesViewModel() {

    private val stateLiveData = MutableLiveData<State>()

    private val clickedArticleMutableLiveData = MutableLiveData<Article>()

    override val clickedArticleLiveData = clickedArticleMutableLiveData

    override fun setClickedArticle(article: Article) {
        clickedArticleMutableLiveData.value = article
    }

    override fun state(): LiveData<State> = stateLiveData

    override val articleListLiveData: LiveData<List<Article>> = newsHeadlinesRepository.articleListLiveData

    override fun fetchNewsHeadlines() {
        coroutineScope.launch {
            try {
                stateLiveData.value = State.Loading
                newsHeadlinesRepository.fetchNewsHeadlines()
            } catch (e: Exception) {
                Log.e("network error", e.message.orEmpty())
                stateLiveData.value = State.Error
            }
        }

    }

}