package com.example.headlines.viewmodel.injection

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
}