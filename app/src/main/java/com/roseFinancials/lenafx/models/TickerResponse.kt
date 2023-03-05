package com.roseFinancials.lenafx.models

import kotlinx.serialization.Serializable

@Serializable
data class TickerResponse(
    val date: String?,
    val close: Double?,
    val high: Double?,
    val low: Double?,
    val open: Double?,
    val volume: Long?,
    val adjClose: Double?,
    val adjHigh: Double?,
    val adjLow: Double?,
    val adjOpen: Double?,
    val adjVolume: Double?,
    val divCash: Double?,
    val splitFactor: Double?
)
