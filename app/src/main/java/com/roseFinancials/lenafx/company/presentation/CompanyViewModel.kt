package com.roseFinancials.lenafx.company.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roseFinancials.lenafx.stocks.data.StocksStateRepository
import kotlinx.coroutines.launch

class CompanyViewModel(
    private val stocksStateRepository: StocksStateRepository
): ViewModel() {
    val stocksStateFlow = stocksStateRepository.stocksState

    fun updateIndex(index: String?) {
        viewModelScope.launch { stocksStateRepository.updateIndex(index) }
    }
    fun updateRange(range: String) {
        viewModelScope.launch { stocksStateRepository.updateRange(range) }
    }
    fun updateInterval(interval: String) {
        viewModelScope.launch { stocksStateRepository.updateInterval(interval) }
    }

    init { resetScreen() }

    private fun resetScreen() {
        viewModelScope.launch {
            stocksStateRepository.resetState()
        }
    }
}