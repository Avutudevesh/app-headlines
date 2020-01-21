package com.example.headlines.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

    private val authorName: TextView = itemView.findViewById(R.id.author)
    private val title: TextView = itemView.findViewById(R.id.title)
    private val description: TextView = itemView.findViewById(R.id.description)

    fun bind(item: Article) {
        authorName.text = item.author
        title.text = item.title
        description.text = item.description
    }
}