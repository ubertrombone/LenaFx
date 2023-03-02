package com.roseFinancials.lenafx.ui.composables.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.roseFinancials.lenafx.destinations.Destination
import com.roseFinancials.lenafx.destinations.StocksScreenDestination

@Composable
fun SecondBottomBar(navController: NavHostController) {
    // TODO
}

enum class SecondBottomBarItem(
    val direction: Destination,
    val icon: ImageVector,
    val text: String
) {
    Stocks(StocksScreenDestination, Icons.Default.Search,"Company"),
}