package com.example.headlines.viewmodel

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

    override fun state(): LiveData<State> = stateLiveData

    override fun fetchNewsHeadlines() {
        coroutineScope.launch {
            try {
                stateLiveData.value = State.Loading
                val data = newsHeadlinesRepository.fetchNewsHeadlines()
                stateLiveData.value = State.Success(data)
            } catch (e: Exception) {
                stateLiveData.value = State.Error
            }
        }

    }

}