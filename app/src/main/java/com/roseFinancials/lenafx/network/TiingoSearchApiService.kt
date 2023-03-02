package com.roseFinancials.lenafx.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.roseFinancials.lenafx.utils.Constants.BASE_URL
import com.roseFinancials.lenafx.models.SearchQueryResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url

@OptIn(ExperimentalSerializationApi::class)
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
    .baseUrl(BASE_URL)
    .build()

interface TiingoSearchApiService {
    @GET
    suspend fun getCompanies(@Url params: String): List<SearchQueryResponse>
}

object TiingoSearchApi {
    val retrofitService: TiingoSearchApiService by lazy {
        retrofit.create(TiingoSearchApiService::class.java)
    }
}