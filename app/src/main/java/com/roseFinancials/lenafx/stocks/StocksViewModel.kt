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

@HiltViewModel
class StocksViewModel @Inject constructor(
    private val stocksStateRepository: StocksStateRepository,
    private val apiDataRepository: ApiDataRepository
): ResetViewModel() {
    override val stocksState = stocksStateRepository.stocksState
    override val apiState = apiDataRepository.apiState
    val indexState = apiDataRepository.indexState
    val tickerState = apiDataRepository.tickerState
    val tickerIndexPairs = apiDataRepository.tickerIndexPairs
    val regressionValues = apiDataRepository.regressionValues
    val betaSlope = apiDataRepository.betaSlope
    val yIntercept = apiDataRepository.yIntercept

    private val _loadingState = MutableStateFlow(LoadingState.EMPTY)
    val loadingState: StateFlow<LoadingState> = _loadingState.asStateFlow()

    fun callApis() {
        _loadingState.update { LoadingState.LOADING }

        viewModelScope.launch {
            apiDataRepository.callApis(
                ticker = stocksState.value.ticker!!,
                index = stocksState.value.index,
                range = stocksState.value.dateRange,
                interval = stocksState.value.interval
            )
            apiDataRepository.collectGraphData()
            println("DATA: ${tickerIndexPairs.value}")
            _loadingState.update { LoadingState.RESULTS }
        }
    }

    fun updateApiState(state: Boolean) { viewModelScope.launch { apiDataRepository.updateState(state) } }
    fun updateLoadingState(state: LoadingState) { _loadingState.update { state } }
    override fun resetState() { viewModelScope.launch { stocksStateRepository.resetState() } }
    override fun resetApiState() { viewModelScope.launch { apiDataRepository.resetApiState() } }
}