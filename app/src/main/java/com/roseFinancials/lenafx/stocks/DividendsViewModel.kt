package com.roseFinancials.lenafx.stocks

import androidx.lifecycle.viewModelScope
import com.roseFinancials.lenafx.data.repositories.StocksStateRepository
import com.roseFinancials.lenafx.utils.ResetViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DividendsViewModel @Inject constructor(private val stocksStateRepository: StocksStateRepository) : ResetViewModel() {
    val stocksState = stocksStateRepository.stocksState

    override fun resetState() {
        viewModelScope.launch {
            stocksStateRepository.resetState()
        }
    }
}