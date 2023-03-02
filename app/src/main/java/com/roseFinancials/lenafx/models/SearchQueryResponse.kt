package com.roseFinancials.lenafx.models

import kotlinx.serialization.Serializable

@Serializable
data class SearchQueryResponse(
    val name: String?,
    val ticker: String?,
    val permaTicker: String?,
    val openFIGIComposite: String?,
    val assetType: String?,
    val isActive: Boolean?,
    val countryCode: String?
)