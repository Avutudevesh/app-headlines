package com.example.headlines.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Provides
    fun provideNewsApiService(moshi: Moshi, okHttpClient: OkHttpClient): NewsApiService {
        val baseUrl = ""
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    fun providesNewsHeadlinesRepository(newsHeadlinesRepositoryImpl: NewsHeadlinesRepositoryImpl)
            : NewsHeadlinesRepository = newsHeadlinesRepositoryImpl
}