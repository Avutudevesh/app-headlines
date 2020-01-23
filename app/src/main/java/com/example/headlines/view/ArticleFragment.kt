package com.example.headlines.view

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.headlines.R
import com.example.headlines.network.Article
import kotlinx.android.synthetic.main.article_fragment.*
import java.lang.IllegalArgumentException

class ArticleFragment : Fragment() {

    private val articleArgument by lazy {
        arguments?.get(INTENT_EXTRA_ARTICLE) as? Article
            ?: throw IllegalArgumentException("Argument to passed for key: $INTENT_EXTRA_ARTICLE")
    }

    companion object {
        private const val INTENT_EXTRA_ARTICLE = "article"
        fun newInstance(article: Article) = ArticleFragment::class.java.newInstance().apply {
            arguments = bundleOf(INTENT_EXTRA_ARTICLE to article)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_button.setOnClickListener {
            activity?.onBackPressed()
        }
        articleArgument.apply {
            article_title.text = title
            article_description.text = description
            article_publish_date.text = publishedAt
            article_source.text = source.name
            urlToImage?.let {
                Glide.with(article_image.context).load(it)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .fitCenter()
                    .into(article_image)
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        (activity as AppCompatActivity).apply {
//            supportActionBar?.hide()
//        }
    }
}