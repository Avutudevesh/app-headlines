package com.example.headlines.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


abstract class NewsHeadlinesViewModel : ViewModel() {

    sealed class State {
        data class Success(val result: List<String>) : State()
        object Loading : State()
        object Error : State()
    }

    abstract fun state(): LiveData<State>

    abstract fun fetchNewsHeadlines()
}