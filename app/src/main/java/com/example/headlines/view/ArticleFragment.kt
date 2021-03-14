package com.example.headlines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.headlines.R
import com.example.headlines.compose.views.HeadlineArticleScreen
import com.example.headlines.network.Article
import java.lang.IllegalArgumentException

class ArticleFragment : Fragment() {

    private val articleArgument by lazy {
        arguments?.get(INTENT_EXTRA_ARTICLE) as? Article
            ?: throw IllegalArgumentException("Argument to passed for key: $INTENT_EXTRA_ARTICLE")
    }

    companion object {
        private const val INTENT_EXTRA_ARTICLE = "article"
        fun newInstance(article: Article?) = ArticleFragment::class.java.newInstance().apply {
            arguments = bundleOf(INTENT_EXTRA_ARTICLE to article)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_compose_fragment, container, false).apply {
            findViewById<ComposeView>(R.id.article_compose_root).setContent {
                MaterialTheme {
                    HeadlineArticleScreen(article = articleArgument){
                        activity?.onBackPressed()
                    }
                }
            }
        }
    }
}