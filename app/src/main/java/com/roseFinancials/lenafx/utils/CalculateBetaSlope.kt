package com.roseFinancials.lenafx.utils

import kotlin.math.pow

fun calculateBetaSlope(data: List<Pair<Double, Double>>): Double {
    val (tickerMean, indexMean) = data.unzip().toList().map { it.average() }

    val numerator = data.sumOf { pair -> (pair.second - indexMean) * (pair.first - tickerMean) }
    val denominator = data.sumOf { (it.second - indexMean).pow(2) }

    return numerator/denominator
}