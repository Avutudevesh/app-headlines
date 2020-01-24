package com.example.headlines.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.headlines.R
import com.example.headlines.network.Article

class ArticleActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context, article: Article): Intent =
            Intent(context, ArticleActivity::class.java).putExtra("article", article)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.ArticleTheme)
        setContentView(R.layout.activity_article)
        supportFragmentManager.beginTransaction()
            .add(
                R.id.article_fragment_container,
                ArticleFragment.newInstance(intent.getParcelableExtra("article"))
            )
            .commit()
    }
}