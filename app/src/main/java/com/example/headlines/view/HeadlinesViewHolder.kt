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

class HeadlinesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

    fun bind(item: Article) {
        source.text = item.source.name
        title.text = item.title
        publishDate.text = item.publishedAt
        loadBackGroundImage(item.urlToImage)
    }

    fun clearImageLoading() {
        Glide.with(headlineImage.context).clear(headlineImage)
    }

    private fun loadBackGroundImage(url: String) {
        Glide.with(itemView.context).load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .fitCenter()
            .into(headlineImage)
    }
}