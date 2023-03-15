package com.roseFinancials.lenafx.utils

import androidx.lifecycle.ViewModel
import com.roseFinancials.lenafx.data.states.StocksState
import kotlinx.coroutines.flow.StateFlow

abstract class ResetViewModel: ViewModel() {
    abstract val stocksState: StateFlow<StocksState>
    abstract val apiState: StateFlow<Boolean>

    abstract fun resetState()
    abstract fun resetApiState()
    fun onBackPressed() {
        resetState()
        resetApiState()
    }
}