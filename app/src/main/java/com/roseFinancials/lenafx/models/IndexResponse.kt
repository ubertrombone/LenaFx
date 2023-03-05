package com.roseFinancials.lenafx.models

import kotlinx.serialization.Serializable

@Serializable
data class IndexResponse(
    val chart: Chart
)

@Serializable
data class Chart(
    val result: List<Result>,
    val error: Boolean?
)

@Serializable
data class Result(
    val meta: Meta,
    val timestamp: List<Long?>,
    val indicators: Indicators
)

@Serializable
data class Meta(
    val currency: String?,
    val symbol: String?,
    val exchangeName: String?,
    val instrumentType: String?,
    val firstTradeDate: Long?,
    val regularMarketTime: Long?,
    val gmtoffset: Long?,
    val timezone: String?,
    val exchangeTimezoneName: String?,
    val regularMarketPrice: Double?,
    val chartPreviousClose: Double?,
    val priceHint: Int?,
    val currentTradingPeriod: CurrentTradingPeriod,
    val dataGranularity: String?,
    val range: String?,
    val validRanges: List<String?>
)

@Serializable
data class CurrentTradingPeriod(
    val pre: TradingPeriod,
    val regular: TradingPeriod,
    val post: TradingPeriod
)

@Serializable
data class TradingPeriod(
    val timezone: String?,
    val end: Long?,
    val start: Long?,
    val gmtoffset: Long?
)

@Serializable
data class Indicators(
    val quote: List<Quote>,
    val adjclose: List<AdjClose>
)

@Serializable
data class Quote(
    val volume: List<Long?>,
    val high: List<Double?>,
    val open: List<Double?>,
    val low: List<Double?>,
    val close: List<Double?>
)

@Serializable
data class AdjClose(
    val adjclose: List<Double?>
)
