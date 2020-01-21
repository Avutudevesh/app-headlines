package com.example.headlines.inject

import com.example.headlines.MainActivity
import com.example.headlines.network.NetworkModule
import com.example.headlines.viewmodel.injection.NewsHeadlinesViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindings {
    @ContributesAndroidInjector(modules = [NewsHeadlinesViewModelModule::class, NetworkModule::class])
    abstract fun bindMainActivity(): MainActivity
}