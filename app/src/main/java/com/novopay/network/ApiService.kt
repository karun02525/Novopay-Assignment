package com.novopay.network

import com.novopay.model.ApiResponse
import com.novopay.model.DataSource
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/top-headlines")
    fun getNewsListAsync(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Deferred<Response<ApiResponse>>

    @GET("/v2/sources")
    fun getSourcesAsync(@Query("apiKey") apiKey: String): Deferred<Response<DataSource>>


}