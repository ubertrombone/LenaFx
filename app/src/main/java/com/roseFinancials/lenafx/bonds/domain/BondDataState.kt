package com.roseFinancials.lenafx.bonds.domain

import kotlinx.serialization.Serializable

@Serializable
data class BondDataState(
    val ticker: String? = null,
    val dateRange: String = "1y",
    val interval: String = "1d"
)
