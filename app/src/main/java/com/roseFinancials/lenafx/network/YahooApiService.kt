package com.roseFinancials.lenafx.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.roseFinancials.lenafx.models.YahooResponse
import com.roseFinancials.lenafx.utils.Constants.YAHOO_BASE_URL
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface YahooApiService {
    @GET
    suspend fun getHistory(
        @Url ticker: String,
        @Query("range") range: String,
        @Query("interval") interval: String,
        @Query("events") events: String = "div"
    ): YahooResponse

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        fun create(): YahooApiService {
            return Retrofit.Builder()
                .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
                .baseUrl(YAHOO_BASE_URL)
                .build()
                .create(YahooApiService::class.java)
        }
    }
}