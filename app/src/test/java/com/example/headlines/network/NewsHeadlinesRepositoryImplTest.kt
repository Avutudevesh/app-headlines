package com.example.headlines.network

import com.example.headlines.db.HeadlinesDao
import com.example.headlines.db.HeadlinesDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalStateException

@RunWith(MockitoJUnitRunner::class)
class NewsHeadlinesRepositoryImplTest {

    private lateinit var subject: NewsHeadlinesRepositoryImpl

    @Mock
    private lateinit var mockNewsApiService: NewsApiService
    @Mock
    private lateinit var mockHeadlinesDatabase: HeadlinesDatabase
    @Mock
    private lateinit var mockHeadlineDao: HeadlinesDao

    private var article: Article = Article(Source(), "title", "image")

    private val listArticles = listOf(article)

    private var mockNewsData: NewsData = NewsData(" ", 12, listArticles)

    @Before
    fun setUp() {
        given(mockHeadlinesDatabase.headlinesDao()).willReturn(mockHeadlineDao)
        subject = NewsHeadlinesRepositoryImpl(mockNewsApiService, mockHeadlinesDatabase)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `testFetchNewsHeadlines - data fetch success`() = runBlocking {
        given(mockNewsApiService.getNewsHeadlines()).willReturn(mockNewsData)

        subject.fetchNewsHeadlines()

        then(mockHeadlinesDatabase.headlinesDao()).should(Mockito.times(1))
            .saveArticles(listArticles)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `testFetchNewsHeadlines - data fetch error`() = runBlocking {
        given(mockNewsApiService.getNewsHeadlines()).willThrow(IllegalStateException())

        try {
            subject.fetchNewsHeadlines()
            fail("Should have thrown exception here")
        } catch (e: Exception) {
            // test success
        }
    }

}