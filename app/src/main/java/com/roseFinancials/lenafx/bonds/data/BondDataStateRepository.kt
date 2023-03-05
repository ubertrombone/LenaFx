package com.roseFinancials.lenafx.bonds.data

import com.roseFinancials.lenafx.bonds.domain.BondDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class BondDataStateRepository {
    private val _bondDataState = MutableStateFlow(BondDataState())
    val bondDataState: StateFlow<BondDataState> = _bondDataState.asStateFlow()

    suspend fun updateTicker(ticker: String) = withContext(Dispatchers.IO) { _bondDataState.update { it.copy(ticker = ticker) } }
    suspend fun updateRange(range: String) = withContext(Dispatchers.IO) { _bondDataState.update { it.copy(dateRange = range) } }
    suspend fun updateInterval(interval: String) = withContext(Dispatchers.IO) { _bondDataState.update { it.copy(interval = interval) } }
    suspend fun resetState() = withContext(Dispatchers.IO) { _bondDataState.value = BondDataState() }
}