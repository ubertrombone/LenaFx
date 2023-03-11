package com.roseFinancials.lenafx.data.states

import com.roseFinancials.lenafx.utils.Constants.INDICES

data class StocksState(
    val ticker: String? = null,
    val dateRange: String = "1y",
    val index: String = INDICES.first().first,
    val interval: String = "1d"
)
