package com.example.headlines.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.headlines.network.Article

@Dao
abstract class HeadlinesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveArticles(articles: List<Article>)

    @Query("SELECT * FROM article")
    abstract fun loadArticles(): LiveData<List<Article>>

}