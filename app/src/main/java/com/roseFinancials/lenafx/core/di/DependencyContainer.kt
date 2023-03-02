package com.roseFinancials.lenafx.core.di

import androidx.lifecycle.SavedStateHandle
import com.roseFinancials.lenafx.MainActivity
import com.roseFinancials.lenafx.MainViewModel
import com.roseFinancials.lenafx.bonds.data.BondDataStateRepository
import com.roseFinancials.lenafx.bonds.presentation.BondsViewModel
import com.roseFinancials.lenafx.company.presentation.CompanyViewModel
import com.roseFinancials.lenafx.index.IndexViewModel
import com.roseFinancials.lenafx.stocks.data.StocksStateRepository
import com.roseFinancials.lenafx.stocks.presentation.StocksViewModel

class DependencyContainer(val activity: MainActivity) {
    val stocksStateRepository: StocksStateRepository by lazy { StocksStateRepository() }
    val bondDataStateRepository: BondDataStateRepository by lazy { BondDataStateRepository() }

    @Suppress("UNCHECKED_CAST")
    fun <T> createViewModel(modelClass: Class<T>, handle: SavedStateHandle): T {
        return when (modelClass) {
            MainViewModel::class.java -> MainViewModel(stocksStateRepository)
            CompanyViewModel::class.java -> CompanyViewModel(stocksStateRepository)
            IndexViewModel::class.java -> IndexViewModel(stocksStateRepository)
            StocksViewModel::class.java -> StocksViewModel(stocksStateRepository)
            BondsViewModel::class.java -> BondsViewModel(bondDataStateRepository)
            else -> throw RuntimeException("Unknown view model $modelClass")
        } as T
    }
}