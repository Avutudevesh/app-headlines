package com.example.headlines.inject

import com.example.headlines.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindings {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}