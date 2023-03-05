package com.roseFinancials.lenafx.etfs.domain

import kotlinx.serialization.Serializable

@Serializable
data class EtfDataState(
    val ticker: String? = null,
    val dateRange: String = "1y",
    val interval: String = "1d"
)
