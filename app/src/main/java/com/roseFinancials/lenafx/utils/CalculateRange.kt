package com.roseFinancials.lenafx.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDateTime

enum class CalculateRange(val attribute: String, val milliseconds: Long) {
    FIVE_DAYS(attribute = "5d", milliseconds = 432000000),
    SIX_MONTHS(attribute = "6mo", milliseconds = 15770000000),
    ONE_YEAR(attribute = "1y", milliseconds = 31540000000),
    TWO_YEARS(attribute = "2y", milliseconds = 63070000000),
    FIVE_YEARS(attribute = "5y", milliseconds = 157700000000),
    TEN_YEARS(attribute = "10y", milliseconds = 315400000000);

    @SuppressLint("SimpleDateFormat")
    fun getDateFromRange(): String? {
        val dateNow = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(LocalDateTime.now().toString())?.time
        return dateNow?.let { SimpleDateFormat("yyyy-MM-dd").format(dateNow - this.milliseconds) }
    }
}