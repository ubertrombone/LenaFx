package com.roseFinancials.lenafx.etfs.data

import com.roseFinancials.lenafx.etfs.domain.EtfDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class EtfDataStateRepository {
    private val _etfDataState = MutableStateFlow(EtfDataState())
    val etfDataState: StateFlow<EtfDataState> = _etfDataState.asStateFlow()

    suspend fun updateTicker(ticker: String) = withContext(Dispatchers.IO) { _etfDataState.update { it.copy(ticker = ticker) } }
    suspend fun updateRange(range: String) = withContext(Dispatchers.IO) { _etfDataState.update { it.copy(dateRange = range) } }
    suspend fun updateInterval(interval: String) = withContext(Dispatchers.IO) { _etfDataState.update { it.copy(interval = interval) } }
    suspend fun resetState() = withContext(Dispatchers.IO) { _etfDataState.value = EtfDataState() }
}