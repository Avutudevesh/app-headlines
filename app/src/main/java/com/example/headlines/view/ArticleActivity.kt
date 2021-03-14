package com.example.headlines.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.example.headlines.R
import com.example.headlines.compose.views.HeadlineArticleScreen
import com.example.headlines.network.Article

class ArticleActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context, article: Article): Intent =
            Intent(context, ArticleActivity::class.java).putExtra("article", article)
    }

    private val article: Article?
        get() = intent.getParcelableExtra("article")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.ArticleTheme)
        article?.let {
            setContent {
                MaterialTheme {
                    HeadlineArticleScreen(article = it) {
                        this.onBackPressed()
                    }
                }
            }
        }
    }
}