package com.roseFinancials.lenafx.data.repositories

import com.roseFinancials.lenafx.charty.linearregression.model.LinearRegressionData
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
import kotlin.math.pow

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

    private val _tickerIndexPairs = MutableStateFlow<List<LinearRegressionData>>(listOf())
    val tickerIndexPairs = _tickerIndexPairs.asStateFlow()

    private val _regressionValues = MutableStateFlow<List<LinearRegressionData>>(listOf())
    val regressionValues = _regressionValues.asStateFlow()

    private val _betaSlope = MutableStateFlow(0.0)
    val betaSlope = _betaSlope.asStateFlow()

    private val _yIntercept = MutableStateFlow(0.0)
    val yIntercept = _yIntercept.asStateFlow()

    suspend fun updateState(state: Boolean) = withContext(Dispatchers.IO) { _apiState.update { state } }
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

    private suspend fun alignTickerAndIndexData() = withContext(Dispatchers.IO) {
        _tickerIndexPairs.update {
            (tickerState.value + indexState.value)
                .groupBy(keySelector = { it.date }, valueTransform = { it.close })
                .filter { it.value.size == 2 }
                .filterValues { !it.contains(null) }
                .values.map { LinearRegressionData(it.last()!!.toFloat(), it.first()!!.toFloat()) }
        }
    }

    private suspend fun calculateBetaSlope() = withContext(Dispatchers.IO) {
        val indexMean = _tickerIndexPairs.value.map { it.xValue.toString().toFloat() }.average()
        val tickerMean = _tickerIndexPairs.value.map { it.yValue }.average()

        val numerator = _tickerIndexPairs.value.sumOf { point -> (point.xValue.toString().toFloat() - indexMean) * (point.yValue - tickerMean) }
        val denominator = _tickerIndexPairs.value.sumOf { point -> (point.xValue.toString().toFloat() - indexMean).pow(2) }

        _betaSlope.update { numerator/denominator }
    }

    private suspend fun calculateYIntercept() = withContext(Dispatchers.IO) {
        _yIntercept.update {
            _tickerIndexPairs.value
                .sumOf { point -> point.yValue - (point.xValue.toString().toFloat() * _betaSlope.value) } / _tickerIndexPairs.value.size
        }
    }

    private suspend fun calculateRegressionValues() = withContext(Dispatchers.IO) {
        _regressionValues.update {
            _tickerIndexPairs.value.map { point ->
                LinearRegressionData(
                    xValue = point.xValue,
                    yValue = ((_betaSlope.value * point.xValue.toString().toFloat()) + _yIntercept.value).toFloat()
                )
            }
        }
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

    suspend fun collectGraphData() = withContext(Dispatchers.IO) {
        alignTickerAndIndexData()
        calculateBetaSlope()
        calculateYIntercept()
        calculateRegressionValues()
    }

    suspend fun resetApiState() = withContext(Dispatchers.IO) {
        _apiState.update { true }
        _dividendsScreenState.update { false }
        _tickerState.update { listOf() }
        _dividendsState.update { listOf() }
        _indexState.update { listOf() }
        _tickerIndexPairs.update { listOf() }
        _regressionValues.update { listOf() }
        _betaSlope.update { 0.0 }
        _yIntercept.update { 0.0 }
    }
}