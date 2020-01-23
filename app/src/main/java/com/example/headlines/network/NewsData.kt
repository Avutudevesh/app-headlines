package com.example.headlines.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsData(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) : Parcelable

@Parcelize
data class Article(
    val source: Source,
    val title: String,
    val urlToImage: String?,
    val description: String? = " ",
    val publishedAt: String? = " "
) : Parcelable

@Parcelize
data class Source(
    val id: String? = "",
    val name: String = " "
) : Parcelable