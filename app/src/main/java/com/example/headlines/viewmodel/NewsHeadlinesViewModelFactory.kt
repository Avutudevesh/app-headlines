package com.example.headlines.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.headlines.network.NewsHeadlinesRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NewsHeadlinesViewModelFactory @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val headlinesRepository: NewsHeadlinesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsHeadlinesViewModelImpl(headlinesRepository, coroutineScope) as T
    }

}