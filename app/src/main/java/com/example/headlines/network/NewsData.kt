package com.example.headlines.network

data class NewsData(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val title: String,
    val urlToImage: String,
    val description: String,
    val publishedAt: String
)

data class Source(
    val id: String? = "",
    val name: String
)