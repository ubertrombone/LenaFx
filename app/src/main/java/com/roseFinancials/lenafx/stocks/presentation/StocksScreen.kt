package com.roseFinancials.lenafx.stocks.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.roseFinancials.lenafx.MainViewModel
import com.roseFinancials.lenafx.core.viewmodel.activityViewModel
import com.roseFinancials.lenafx.core.viewmodel.viewModel

@Destination
@Composable
fun StocksScreen(
    navController: NavController,
    viewModel: StocksViewModel = viewModel()
) {
    val mainViewModel = activityViewModel<MainViewModel>()
    BackHandler {
        mainViewModel.resetStocksState()
        navController.popBackStack()
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
    ) {
        Text("Ticker: ${viewModel.stocksState.ticker}")
        Text("Index: ${viewModel.stocksState.index}")
        Text("Range: ${viewModel.stocksState.dateRange}")
        Text("Interval: ${viewModel.stocksState.interval}")
    }
}