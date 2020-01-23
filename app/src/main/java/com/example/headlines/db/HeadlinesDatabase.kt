package com.example.headlines.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.headlines.network.Article

@Database(entities = [Article::class], version = 1)
abstract class HeadlinesDatabase : RoomDatabase() {
    abstract fun headlinesDao(): HeadlinesDao
}