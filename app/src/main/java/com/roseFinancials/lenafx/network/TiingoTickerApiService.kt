package com.roseFinancials.lenafx.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.roseFinancials.lenafx.BuildConfig
import com.roseFinancials.lenafx.models.TickerResponse
import com.roseFinancials.lenafx.utils.Constants
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface TiingoTickerApiService {
    @GET
    suspend fun getHistory(
        @Url extension: String,
        @Query("startDate") startDate: String,
        @Query("token") token: String = BuildConfig.TIINGO_KEY
    ): List<TickerResponse>

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        fun create(): TiingoTickerApiService {
            return Retrofit.Builder()
                .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
                .baseUrl(Constants.TIINGO_BASE_URL)
                .build()
                .create(TiingoTickerApiService::class.java)
        }
    }
}