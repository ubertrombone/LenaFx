package com.roseFinancials.lenafx.models

import kotlinx.serialization.Serializable

@Serializable
data class DividendsModel(
    val date: String,
    val dividends: Double
)
