package com.roseFinancials.lenafx.stocks

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.popUpTo
import com.roseFinancials.lenafx.NavGraphs

@Destination
@Composable
fun StocksScreen(
    navController: NavController,
    viewModel: StocksViewModel = hiltViewModel()
) {
    val stocks = viewModel.stocksState.collectAsState().value
    val backStackEntry = navController.previousBackStackEntry

    BackHandler {
        viewModel.resetState()
        backStackEntry?.let { navController.navigate(backStackEntry.destination.route!!) {
            popUpTo(NavGraphs.root) {
                saveState = false
            }
            restoreState = false
        }} ?: navController.popBackStack()
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
    ) {
        Text("Ticker: ${stocks.ticker}")
        Text("Index: ${stocks.index}")
        Text("Range: ${stocks.dateRange}")
        Text("Interval: ${stocks.interval}")
    }
}