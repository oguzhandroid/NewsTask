package com.rent.donanmhabertask.data.service

import com.rent.donanmhabertask.data.model.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("dev/and/api/newest")
    fun getResponse(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): Single<NewsResponse>
}