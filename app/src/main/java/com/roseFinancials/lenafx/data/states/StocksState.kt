package com.roseFinancials.lenafx.data.states

data class StocksState(
    val ticker: String? = null,
    val dateRange: String = "1y",
    val index: String? = null,
    val interval: String = "1d"
)
