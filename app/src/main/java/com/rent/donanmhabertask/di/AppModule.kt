package com.rent.donanmhabertask.di

import android.content.Context
import androidx.room.Room
import com.rent.donanmhabertask.data.service.NewsAPI
import com.rent.donanmhabertask.data.service.NewsDao
import com.rent.donanmhabertask.data.service.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //https://api.donanimhaber.com/dev/and/api/newest?pageIndex=0&pageSize=15
    val BASE_URL = "https://api.donanimhaber.com/"

    @Singleton
    @Provides
    fun provideOkHTTPClient() = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val originalHTTPUrl = original.url
            val url = originalHTTPUrl.newBuilder()
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)
            chain.proceed(requestBuilder.build())
        }
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) =
        provideRetrofit(provideOkHTTPClient()).create(NewsAPI::class.java)



}