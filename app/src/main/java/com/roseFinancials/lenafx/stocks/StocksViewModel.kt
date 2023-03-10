package com.roseFinancials.lenafx.stocks

import androidx.lifecycle.viewModelScope
import com.roseFinancials.lenafx.data.repositories.ApiDataRepository
import com.roseFinancials.lenafx.data.repositories.StocksStateRepository
import com.roseFinancials.lenafx.utils.LoadingState
import com.roseFinancials.lenafx.utils.ResetViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class StocksViewModel @Inject constructor(
    private val stocksStateRepository: StocksStateRepository,
    private val apiDataRepository: ApiDataRepository
): ResetViewModel() {
    override val stocksState = stocksStateRepository.stocksState
    override val apiState = apiDataRepository.apiState
    val tickerState = apiDataRepository.tickerState
    val indexState = apiDataRepository.indexState

    private val _loadingState = MutableStateFlow(LoadingState.EMPTY)
    val loadingState: StateFlow<LoadingState> = _loadingState.asStateFlow()

    fun callApis() {
        _loadingState.update { LoadingState.LOADING }

        viewModelScope.launch {
            apiDataRepository.callApis(
                tickerExtension = "daily/${stocksState.value.ticker}/prices",
                index = "%5E${stocksState.value.index}",
                range = stocksState.value.dateRange,
                interval = stocksState.value.interval
            )
            _loadingState.update { LoadingState.RESULTS }
        }
    }

    fun calculateBetaSlope(data: List<Pair<Double, Double>>): Double {
        val (tickerMean, indexMean) = data.unzip().toList().map { it.average() }

        val numerator = data.sumOf { pair -> (pair.second - indexMean) * (pair.first - tickerMean) }
        val denominator = data.sumOf { (it.second - indexMean).pow(2) }

        return numerator/denominator
    }

    override fun updateApiState(state: Boolean) { viewModelScope.launch { apiDataRepository.updateState(state) } }
    override fun resetState() { viewModelScope.launch { stocksStateRepository.resetState() } }
    override fun updateDividendsState(state: Boolean) { viewModelScope.launch { apiDataRepository.updateDividendsState(state) } }
}