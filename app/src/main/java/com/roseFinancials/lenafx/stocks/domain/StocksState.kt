package com.roseFinancials.lenafx.stocks.domain

data class StocksState(
    val ticker: String? = null,
    val dateRange: String = "1y",
    val index: String? = null,
    val interval: String = "1d"
)
