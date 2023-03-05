package com.roseFinancials.lenafx.stocks.data

import com.roseFinancials.lenafx.stocks.domain.StocksState
import com.roseFinancials.lenafx.utils.Constants.INDICES
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class StocksStateRepository {
    private val _stocksState = MutableStateFlow(StocksState(index = INDICES.first().first))
    val stocksState: StateFlow<StocksState> = _stocksState.asStateFlow()

    suspend fun updateTicker(ticker: String?) = withContext(Dispatchers.IO) { _stocksState.update { it.copy(ticker = ticker) } }
    suspend fun updateIndex(index: String?) = withContext(Dispatchers.IO) { _stocksState.update { it.copy(index = index) } }
    suspend fun updateRange(range: String) = withContext(Dispatchers.IO) { _stocksState.update { it.copy(dateRange = range) } }
    suspend fun updateInterval(interval: String) = withContext(Dispatchers.IO) { _stocksState.update { it.copy(interval = interval) } }
    suspend fun resetState() = withContext(Dispatchers.IO) { _stocksState.value = StocksState(index = INDICES.first().first) }
}