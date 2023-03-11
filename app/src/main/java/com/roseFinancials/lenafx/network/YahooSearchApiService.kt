package com.roseFinancials.lenafx.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.roseFinancials.lenafx.models.SearchQueryResponse
import com.roseFinancials.lenafx.utils.Constants.YAHOO_BASE_URL
import com.roseFinancials.lenafx.utils.Constants.YAHOO_SEARCH_URL
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface YahooSearchApiService {
    @GET
    suspend fun getSearchResults(
        @Url search: String = "search",
        @Query("q") q: String
    ): SearchQueryResponse

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        @OptIn(ExperimentalSerializationApi::class)
        fun create(): YahooSearchApiService {
            return Retrofit.Builder()
                .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
                .baseUrl("$YAHOO_BASE_URL$YAHOO_SEARCH_URL")
                .build()
                .create(YahooSearchApiService::class.java)
        }
    }
}