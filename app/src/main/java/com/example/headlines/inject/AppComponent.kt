package com.example.headlines.inject

import com.example.headlines.HeadlinesApplication
import com.example.headlines.db.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindings::class,
        FragmentBindings::class,
        DatabaseModule::class
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