package com.example.headlines.compose.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.headlines.R
import com.example.headlines.network.Article
import com.example.headlines.viewmodel.NewsHeadlinesViewModel
import dev.chrisbanes.accompanist.coil.CoilImage


@Composable
fun HeadlinesList(viewModel: NewsHeadlinesViewModel, onArticleClicked: (Article) -> Unit) {
    val newsArticles: List<Article> by viewModel.articleListLiveData.observeAsState(emptyList())
    LazyColumn(modifier = Modifier.background(Color.Gray)) {
        items(newsArticles) { item ->
            HeadlineItem(item, onArticleClicked)
        }
    }

}

@Composable
fun HeadlineItem(article: Article, onArticleClicked: (Article) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 16.dp,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .height(200.dp)
            .clickable { onArticleClicked(article) }) {
        Box {
            article.urlToImage?.let {
                CoilImage(
                    data = it,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier
                    .align(BottomStart)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = article.title,
                    style = TextStyle(color = Color.White, fontSize = 20.sp),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                Row {
                    Text(
                        text = article.source.name,
                        style = TextStyle(color = Color.White, fontSize = 12.sp),
                    )
                    article.publishedAt?.let {
                        Text(
                            text = it,
                            style = TextStyle(color = Color.White, fontSize = 12.sp),
                            modifier = Modifier.padding(start = 24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeadlineArticleScreen(article: Article, onBackClicked: () -> Unit) {
    Box {
        article.urlToImage?.let {
            CoilImage(data = it, contentDescription = null, contentScale = ContentScale.FillBounds)
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .align(TopStart)
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .clickable { onBackClicked() },
            tint = Color.White,
        )
        Column(
            modifier = Modifier
                .align(BottomStart)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                article.title, style = TextStyle(color = Color.White, fontSize = 30.sp),
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Row(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(
                    text = article.source.name,
                    style = TextStyle(color = Color.White, fontSize = 12.sp)
                )
                article.publishedAt?.let { publishDate ->
                    Text(
                        text = publishDate,
                        style = TextStyle(color = Color.White, fontSize = 12.sp),
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }
            }
            article.description?.let {
                Text(
                    it, style = TextStyle(color = Color.White, fontSize = 14.sp),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}