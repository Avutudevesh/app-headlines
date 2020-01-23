package com.example.headlines.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.headlines.network.Article
import com.example.headlines.network.NewsHeadlinesRepository
import com.example.headlines.network.Source
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalStateException

@RunWith(MockitoJUnitRunner::class)
class NewsHeadlinesViewModelImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()


    private lateinit var subject: NewsHeadlinesViewModelImpl

    @Mock
    private lateinit var newsHeadlinesRepository: NewsHeadlinesRepository
    @Mock
    private lateinit var mockStateObserver: Observer<NewsHeadlinesViewModel.State>
    @Mock
    private lateinit var mockClickedArticleObserver: Observer<Article>

    private var article: Article = Article(Source(), "title", "image")

    private var coroutineScope = CoroutineScope(Job() + Dispatchers.Unconfined)

    @Before
    fun setUp() {
        subject = NewsHeadlinesViewModelImpl(newsHeadlinesRepository, coroutineScope)
        subject.state().observeForever(mockStateObserver)
        subject.clickedArticleLiveData.observeForever(mockClickedArticleObserver)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `testFetchNewsHeadlines - then repository fetch news headlines should be called`() =
        runBlockingTest {
            //WHEN
            subject.fetchNewsHeadlines()

            //THEN
            then(newsHeadlinesRepository).should(Mockito.times(1)).fetchNewsHeadlines()
            then(mockStateObserver).should(Mockito.times(1))
                .onChanged(NewsHeadlinesViewModel.State.Loading)
            then(mockStateObserver).shouldHaveNoMoreInteractions()
        }

    @Test
    @ExperimentalCoroutinesApi
    fun `testFetchNewsHeadlines -  when repository return error`() = runBlockingTest {
        //GIVEN
        given(newsHeadlinesRepository.fetchNewsHeadlines()).willThrow(IllegalStateException())

        //WHEN
        subject.fetchNewsHeadlines()

        //THEN
        then(mockStateObserver).should(Mockito.times(1))
            .onChanged(NewsHeadlinesViewModel.State.Loading)
        then(mockStateObserver).should(Mockito.times(1))
            .onChanged(NewsHeadlinesViewModel.State.Error)
        then(mockStateObserver).shouldHaveNoMoreInteractions()
    }

    @Test
    fun testSetClickedArticle() {
        //WHEN
        subject.setClickedArticle(article)

        //THEN
        then(mockClickedArticleObserver).should(Mockito.times(1)).onChanged(article)
        then(mockClickedArticleObserver).shouldHaveNoMoreInteractions()
    }

}