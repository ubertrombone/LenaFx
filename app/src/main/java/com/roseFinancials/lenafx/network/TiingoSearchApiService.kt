package com.roseFinancials.lenafx.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.roseFinancials.lenafx.BuildConfig
import com.roseFinancials.lenafx.models.SearchQueryResponse
import com.roseFinancials.lenafx.utils.Constants
import com.roseFinancials.lenafx.utils.Constants.BASE_URL
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface TiingoSearchApiService {
    @GET
    suspend fun getCompanies(
        @Url extension: String = Constants.SEARCH_URL,
        @Query("query") query: String,
        @Query("limit") limit: Int = Constants.LIMIT,
        @Query("token") token: String = BuildConfig.TIINGO_KEY
    ): List<SearchQueryResponse>

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        fun create(): TiingoSearchApiService {
            return Retrofit.Builder()
                .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
                .baseUrl(BASE_URL)
                .build()
                .create(TiingoSearchApiService::class.java)
        }
    }
}