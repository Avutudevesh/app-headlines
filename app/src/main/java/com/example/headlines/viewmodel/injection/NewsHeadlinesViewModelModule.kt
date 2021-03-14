package com.example.headlines.viewmodel.injection

import androidx.lifecycle.ViewModelProvider
import com.example.headlines.view.MainActivity
import com.example.headlines.viewmodel.NewsHeadlinesViewModel
import com.example.headlines.viewmodel.NewsHeadlinesViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Module
class NewsHeadlinesViewModelModule {

    @Provides
    internal fun provideJob(): Job = Job()

    @Provides
    internal fun provideCoroutineScope(job: Job): CoroutineScope =
        CoroutineScope(job + Dispatchers.Main)

    @Provides
    internal fun provideViewModel(
        activity: MainActivity,
        viewModelFactory: NewsHeadlinesViewModelFactory
    ): NewsHeadlinesViewModel =
        ViewModelProvider(activity, viewModelFactory).get(NewsHeadlinesViewModel::class.java)
}