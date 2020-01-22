package com.example.headlines.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.headlines.network.Article
import com.example.headlines.viewmodel.NewsHeadlinesViewModel
import javax.inject.Inject

class HeadlinesListAdapter @Inject constructor(
    private val viewModel: NewsHeadlinesViewModel
) :
    ListAdapter<Article, HeadlinesViewHolder>(HeadlinesListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesViewHolder {
        return HeadlinesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, viewModel)
    }

    override fun onViewRecycled(holder: HeadlinesViewHolder) {
        super.onViewRecycled(holder)
        holder.clearImageLoading()
    }

}

class HeadlinesListDiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}