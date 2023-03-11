package com.roseFinancials.lenafx.stocks

import androidx.lifecycle.viewModelScope
import com.roseFinancials.lenafx.data.repositories.ApiDataRepository
import com.roseFinancials.lenafx.data.repositories.StocksStateRepository
import com.roseFinancials.lenafx.utils.ResetViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DividendsViewModel @Inject constructor(
    private val stocksStateRepository: StocksStateRepository,
    private val apiDataRepository: ApiDataRepository
) : ResetViewModel() {
    override val stocksState = stocksStateRepository.stocksState
    override val apiState = apiDataRepository.apiState
    val dividendsState = apiDataRepository.dividendsState
    val dividendsScreenState = apiDataRepository.dividendsScreenState

    override fun updateApiState(state: Boolean) { viewModelScope.launch { apiDataRepository.updateState(state) } }
    override fun resetState() { viewModelScope.launch { stocksStateRepository.resetState() } }
    override fun updateDividendsScreenState(state: Boolean) { viewModelScope.launch { apiDataRepository.updateDividendsScreenState(state) } }
}