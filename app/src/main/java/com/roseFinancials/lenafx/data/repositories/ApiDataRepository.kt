package com.roseFinancials.lenafx.data.repositories

import com.roseFinancials.lenafx.models.CloseModel
import com.roseFinancials.lenafx.models.DividendsModel
import com.roseFinancials.lenafx.models.YahooResponse
import com.roseFinancials.lenafx.network.YahooApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiDataRepository @Inject constructor(private val yahooApiService: YahooApiService) {
    private val _apiState = MutableStateFlow(true)
    val apiState: StateFlow<Boolean> = _apiState.asStateFlow()

    private val _tickerState = MutableStateFlow<List<CloseModel>>(listOf())
    val tickerState = _tickerState.asStateFlow()

    private val _dividendsState = MutableStateFlow<List<DividendsModel>>(listOf())
    val dividendsState = _dividendsState.asStateFlow()

    private val _indexState = MutableStateFlow<List<CloseModel>>(listOf())
    val indexState = _indexState.asStateFlow()

    private val _dividendsScreenState = MutableStateFlow(false)
    val dividendsScreenState = _dividendsScreenState.asStateFlow()

    suspend fun updateState(state: Boolean) = withContext(Dispatchers.IO) { _apiState.update { state } }
    suspend fun updateDividendsScreenState(state: Boolean) = withContext(Dispatchers.IO) { _dividendsScreenState.update { state } }
    private suspend fun getCloseValues(response: YahooResponse) = withContext(Dispatchers.IO) {
        val dates = response.chart.result.first().timestamp
            .map { (it ?: 0) * 1000 }
            .map { SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(it)!! }
        val closeValues = response.chart.result.first().indicators.quote.first().close
            .map { it ?: 0.0 }
        return@withContext dates.zip(closeValues).map { CloseModel(it.first, it.second) }
    }

    private suspend fun getDividends(response: YahooResponse) = withContext(Dispatchers.IO) {
        val dividends = response.chart.result.first().events.dividends.values.map { element ->
            element.toString().trim('{', '}').split(",").zipWithNext().single()
        }
        val divCash = dividends.map { it.first.split(":").last().toDouble() }
        val dates = dividends
            .map { it.second.split(":").last().toLong() * 1000 }
            .map { SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(it)!! }
        return@withContext dates.zip(divCash).map { DividendsModel(it.first, it.second) }
    }

    suspend fun callApis(
        ticker: String,
        index: String,
        range: String,
        interval: String
    ) = withContext(Dispatchers.IO) {
        launch {
            val response = yahooApiService.getHistory(ticker, range, interval)
            _tickerState.update { getCloseValues(response) }
            _dividendsState.update { getDividends(response) }
            _dividendsScreenState.update { true }
        }
        launch {
            val response = yahooApiService.getHistory(index, range, interval)
            _indexState.update { getCloseValues(response) }
        }

    }
}