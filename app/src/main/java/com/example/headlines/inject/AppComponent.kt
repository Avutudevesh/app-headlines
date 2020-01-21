package com.example.headlines.inject

import com.example.headlines.HeadlinesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindings::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: HeadlinesApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: HeadlinesApplication)
}