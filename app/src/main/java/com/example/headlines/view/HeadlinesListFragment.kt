package com.example.headlines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.headlines.R
import com.example.headlines.compose.views.HeadlinesList
import com.example.headlines.network.Article
import com.example.headlines.viewmodel.NewsHeadlinesViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HeadlinesListFragment : Fragment() {

    @Inject
    lateinit var headlinesListAdapter: HeadlinesListAdapter

    @Inject
    lateinit var viewModel: NewsHeadlinesViewModel

    private lateinit var callback: OnHeadlineSelectedListener

    fun setOnHeadlineSelectedListener(callback: OnHeadlineSelectedListener) {
        this.callback = callback
    }

    interface OnHeadlineSelectedListener {
        fun onArticleSelected(article: Article)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.headlines_list_compose_fragment, container, false).apply {
            findViewById<ComposeView>(R.id.headlines_list_root).setContent {
                MaterialTheme {
                    HeadlinesList(viewModel) {
                        callback.onArticleSelected(it)
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
