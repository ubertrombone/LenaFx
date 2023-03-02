package com.roseFinancials.lenafx.ui.composables.scaffold

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.roseFinancials.lenafx.destinations.Destination
import com.roseFinancials.lenafx.destinations.StocksScreenDestination

@Composable
fun SecondBottomBar(navController: NavHostController) {
    // TODO
}

enum class SecondBottomBarItem(
    val direction: Destination,
    val text: String
) {
    Stocks(StocksScreenDestination, "Stocks"),
}