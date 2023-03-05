package com.roseFinancials.lenafx.stocks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roseFinancials.lenafx.stocks.data.StocksStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StocksViewModel @Inject constructor(private val stocksStateRepository: StocksStateRepository): ViewModel() {
    val stocksState = stocksStateRepository.stocksState

    fun resetState() {
        viewModelScope.launch {
            stocksStateRepository.resetState()
        }
    }
}