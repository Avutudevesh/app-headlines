package com.example.headlines.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import com.example.headlines.compose.views.HeadlinesList
import com.example.headlines.compose.views.HeadlinesTopBar
import com.example.headlines.viewmodel.NewsHeadlinesViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: NewsHeadlinesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        supportActionBar?.hide()
        setContent {
            MaterialTheme {
                Scaffold(topBar = { HeadlinesTopBar() }) {
                    HeadlinesList(viewModel) {
                        startActivity(ArticleActivity.getStartIntent(this, it))
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchNewsHeadlines()
    }

}
