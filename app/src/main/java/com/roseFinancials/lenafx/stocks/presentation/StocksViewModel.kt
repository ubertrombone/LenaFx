package com.roseFinancials.lenafx.stocks.presentation

import androidx.lifecycle.ViewModel
import com.roseFinancials.lenafx.stocks.data.StocksStateRepository

class StocksViewModel(private val stocksStateRepository: StocksStateRepository): ViewModel() {
    val stocksState = stocksStateRepository.stocksState.value
}