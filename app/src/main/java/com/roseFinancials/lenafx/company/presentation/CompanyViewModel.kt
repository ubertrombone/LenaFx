package com.roseFinancials.lenafx.company.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roseFinancials.lenafx.company.domain.LoadingState
import com.roseFinancials.lenafx.models.SearchQueryResponse
import com.roseFinancials.lenafx.network.TiingoSearchApiService
import com.roseFinancials.lenafx.stocks.data.StocksStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val stocksStateRepository: StocksStateRepository,
    private val tiingoSearchApiService: TiingoSearchApiService
): ViewModel() {
    val stocksStateFlow = stocksStateRepository.stocksState

    fun updateTicker(ticker: String?) {
        viewModelScope.launch { stocksStateRepository.updateTicker(ticker) }
    }
    fun updateIndex(index: String?) {
        viewModelScope.launch { stocksStateRepository.updateIndex(index) }
    }
    fun updateRange(range: String) {
        viewModelScope.launch { stocksStateRepository.updateRange(range) }
    }
    fun updateInterval(interval: String) {
        viewModelScope.launch { stocksStateRepository.updateInterval(interval) }
    }

    private val _loadingState = MutableStateFlow(LoadingState.EMPTY)
    val loadingState: StateFlow<LoadingState> = _loadingState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow(listOf<SearchQueryResponse>())
    val searchResults: StateFlow<List<SearchQueryResponse>> = _searchResults.asStateFlow()

    private var job: Job? = null
    @Suppress("DuplicatedCode")
    fun updateSearchQuery(query: String) {
        job?.cancel()
        if (query.isBlank()) {
            _searchQuery.value = ""
            _loadingState.update { LoadingState.EMPTY }
            return
        }
        _searchQuery.value = query
        _loadingState.update { LoadingState.LOADING }
        if (query.length < 3) {
            _searchResults.update { listOf() }
            return
        }
        job = viewModelScope.launch {
            delay(500)
            _searchResults.update {
                tiingoSearchApiService.getCompanies(query = query)
            }
            _loadingState.update { LoadingState.RESULTS }
        }
    }
}