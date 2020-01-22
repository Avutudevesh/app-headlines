package com.example.headlines.inject

import com.example.headlines.network.NetworkModule
import com.example.headlines.view.HeadlinesListFragment
import com.example.headlines.viewmodel.injection.NewsHeadlinesViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindings {
    @ContributesAndroidInjector(modules = [NewsHeadlinesViewModelModule::class, NetworkModule::class])
    abstract fun bindHeadlinesListFragment(): HeadlinesListFragment
}