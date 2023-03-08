package com.roseFinancials.lenafx.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roseFinancials.lenafx.data.repositories.ApiDataRepository
import com.roseFinancials.lenafx.data.states.StocksState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class ResetViewModel: ViewModel() {
    abstract val stocksState: StateFlow<StocksState>
    abstract val apiState: StateFlow<Boolean>
    abstract val job: Job?

    fun callApi(apiDataRepository: ApiDataRepository) {
        job?.cancel()

        updateJob(
            viewModelScope.launch {
                delay(500)
                apiDataRepository.callApis(
                    tickerExtension = "daily/${stocksState.value.ticker}/prices",
                    index = "%5E${stocksState.value.index}",
                    range = stocksState.value.dateRange,
                    interval = stocksState.value.interval
                )
                updateApiState(false)
            }
        )
    }
    abstract fun updateApiState(state: Boolean)
    abstract fun updateJob(job: Job?)
    abstract fun resetState()
}