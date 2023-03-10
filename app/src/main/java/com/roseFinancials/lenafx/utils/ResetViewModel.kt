package com.roseFinancials.lenafx.utils

import androidx.lifecycle.ViewModel
import com.roseFinancials.lenafx.data.states.StocksState
import kotlinx.coroutines.flow.StateFlow

abstract class ResetViewModel: ViewModel() {
    abstract val stocksState: StateFlow<StocksState>
    abstract val apiState: StateFlow<Boolean>

    abstract fun updateApiState(state: Boolean)
    abstract fun updateDividendsState(state: Boolean)
    abstract fun resetState()
    fun onBackPressed() {
        resetState()
        updateApiState(true)
        updateDividendsState(false)
    }
}