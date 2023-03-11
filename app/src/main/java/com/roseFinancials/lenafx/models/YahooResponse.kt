package com.roseFinancials.lenafx.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class YahooResponse(
    val chart: Chart = Chart()
)

@Serializable
data class Chart(
    val result: List<Result> = listOf()
)

@Serializable
data class Result(
    val timestamp: List<Long?> = listOf(),
    val events: Events = Events(),
    val indicators: Indicators = Indicators()
)

@Serializable
data class Events(
    val dividends: JsonObject = JsonObject(mapOf())
)

@Serializable
data class Indicators(
    val quote: List<Quote> = listOf()
)

@Serializable
data class Quote(
    val volume: List<Long?> = listOf(),
    val high: List<Double?> = listOf(),
    val open: List<Double?> = listOf(),
    val low: List<Double?> = listOf(),
    val close: List<Double?> = listOf()
)
