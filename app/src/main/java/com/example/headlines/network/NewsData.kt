package com.example.headlines.network

data class NewsData(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val author: String? = "",
    val title: String? = "",
    val description: String? = ""
)