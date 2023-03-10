package com.roseFinancials.lenafx.data.repositories

import com.roseFinancials.lenafx.models.IndexResponse
import com.roseFinancials.lenafx.models.TickerResponse
import com.roseFinancials.lenafx.network.TiingoTickerApiService
import com.roseFinancials.lenafx.network.YahooApiService
import com.roseFinancials.lenafx.utils.CalculateRange.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiDataRepository @Inject constructor(
    private val tiingoTickerApiService: TiingoTickerApiService,
    private val yahooApiService: YahooApiService
) {
    private val _apiState = MutableStateFlow(true)
    val apiState: StateFlow<Boolean> = _apiState.asStateFlow()

    private val _tickerState = MutableStateFlow<List<TickerResponse>>(listOf())
    val tickerState = _tickerState.asStateFlow()

    private val _indexState = MutableStateFlow<IndexResponse?>(null)
    val indexState = _indexState.asStateFlow()

    private val _dividendsState = MutableStateFlow(false)
    val dividendsState = _dividendsState.asStateFlow()

    suspend fun updateState(state: Boolean) = withContext(Dispatchers.IO) { _apiState.update { state } }
    suspend fun updateDividendsState(state: Boolean) = withContext(Dispatchers.IO) { _dividendsState.update { state } }

    suspend fun callApis(
        tickerExtension: String,
        index: String,
        range: String,
        interval: String
    ) = withContext(Dispatchers.IO) {
        launch {
            _tickerState.update { tiingoTickerApiService.getHistory(tickerExtension, convertRangeToStartDate(range)!!) }
            _dividendsState.update { true }
        }
        launch {
            _indexState.update { yahooApiService.getHistory(index, range, interval) }
        }

    }

    private fun convertRangeToStartDate(range: String): String? {
        return when (range) {
            FIVE_DAYS.attribute -> FIVE_DAYS.getDateFromRange()
            SIX_MONTHS.attribute -> SIX_MONTHS.getDateFromRange()
            ONE_YEAR.attribute -> ONE_YEAR.getDateFromRange()
            TWO_YEARS.attribute -> TWO_YEARS.getDateFromRange()
            FIVE_YEARS.attribute -> FIVE_YEARS.getDateFromRange()
            TEN_YEARS.attribute -> TEN_YEARS.getDateFromRange()
            else -> throw IOException("Range must be 5d, 6mo, 1y, 2y, 5y, or 10y")
        }
    }
}