package com.example.headlines.network

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsData(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) : Parcelable

@Entity
@Parcelize
data class Article(
    @Embedded val source: Source,
    @PrimaryKey
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