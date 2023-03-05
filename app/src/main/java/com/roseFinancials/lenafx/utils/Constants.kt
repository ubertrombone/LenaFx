package com.roseFinancials.lenafx.utils

object Constants {
    const val BASE_URL = "https://api.tiingo.com/tiingo/"
    const val SEARCH_URL = "utilities/search"
    const val LIMIT = 100
    val RANGES = listOf("5d", "6mo", "1y", "2y", "5y", "10y")
    val INTERVALS = listOf("1m", "2m", "5m", "15m", "30m", "60m", "90m", "1h", "1d", "5d", "1wk", "1mo", "3mo")
    val INDICES = listOf(("GSPC" to "S&P 500"), ("FTSE" to "FTSE 100"))
}