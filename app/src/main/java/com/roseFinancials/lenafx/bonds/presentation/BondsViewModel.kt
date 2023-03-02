package com.roseFinancials.lenafx.bonds.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roseFinancials.lenafx.bonds.data.BondDataStateRepository
import kotlinx.coroutines.launch

class BondsViewModel(private val bondDataStateRepository: BondDataStateRepository): ViewModel() {
    val bondDataStateFlow = bondDataStateRepository.bondDataState

    fun updateTicker(ticker: String) {
        viewModelScope.launch { bondDataStateRepository.updateTicker(ticker) }
    }
    fun updateRange(range: String) {
        viewModelScope.launch { bondDataStateRepository.updateRange(range) }
    }
    fun updateInterval(interval: String) {
        viewModelScope.launch { bondDataStateRepository.updateInterval(interval) }
    }

    init { resetScreen() }

    private fun resetScreen() {
        viewModelScope.launch { bondDataStateRepository.resetState() }
    }
}