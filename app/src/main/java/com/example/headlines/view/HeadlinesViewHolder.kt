package com.example.headlines.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.headlines.R
import com.example.headlines.network.Article
import com.example.headlines.utils.DateTimeFormatterUtil
import com.example.headlines.viewmodel.NewsHeadlinesViewModel

class HeadlinesViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    companion object {
        fun from(parent: ViewGroup): HeadlinesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.headlines_item, parent, false)
            return HeadlinesViewHolder(view)
        }
    }

    private val source: TextView = itemView.findViewById(R.id.source)
    private val title: TextView = itemView.findViewById(R.id.title)
    private val publishDate: TextView = itemView.findViewById(R.id.publish_date)
    private val headlineImage: ImageView = itemView.findViewById(R.id.headline_image)

    fun bind(item: Article, viewModel: NewsHeadlinesViewModel) {
        source.text = item.source.name
        title.text = item.title
        publishDate.text = item.publishedAt?.let { DateTimeFormatterUtil.formatDateTime(it) } ?: " "
        loadBackGroundImage(item.urlToImage)
        itemView.setOnClickListener {
            viewModel.setClickedArticle(item)
        }
    }

    fun clearImageLoading() {
        Glide.with(headlineImage.context).clear(headlineImage)
    }

    private fun loadBackGroundImage(url: String?) {
        url?.let {
            Glide.with(itemView.context).load(it)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .fitCenter()
                .into(headlineImage)
        }
    }
}