package com.roseFinancials.lenafx.models

import kotlinx.serialization.Serializable

@Serializable
data class SearchQueryResponse(val quotes: List<Quotes>)

@Serializable
data class Quotes(
    val shortname: String = "",
    val quoteType: String = "",
    val symbol: String = "",
    val typeDisp: String = "",
    val longname: String = "",
    val exchDisp: String = "",
    val sector: String = "",
    val industry: String = "",
)