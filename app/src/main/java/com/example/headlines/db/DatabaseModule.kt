package com.example.headlines.db

import androidx.room.Room
import com.example.headlines.HeadlinesApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: HeadlinesApplication): HeadlinesDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            HeadlinesDatabase::class.java,
            "HeadlinesDatabase"
        ).build()
    }
}