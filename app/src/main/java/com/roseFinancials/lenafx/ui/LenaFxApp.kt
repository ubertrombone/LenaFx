package com.roseFinancials.lenafx.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.roseFinancials.lenafx.NavGraphs
import com.roseFinancials.lenafx.destinations.*
import com.roseFinancials.lenafx.ui.composables.scaffold.FirstScaffold
import com.roseFinancials.lenafx.ui.composables.scaffold.SearchBottomBar
import com.roseFinancials.lenafx.ui.composables.scaffold.StocksBottomBar
import com.roseFinancials.lenafx.ui.composables.scaffold.TopBar
import com.roseFinancials.lenafx.utils.BottomBars.SEARCH_BOTTOM_BAR
import com.roseFinancials.lenafx.utils.BottomBars.STOCKS_BOTTOM_BAR

@Composable
fun LenaFxApp() {
    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    FirstScaffold(
        startRoute = NavGraphs.root.startRoute,
        navController = navController,
        topBar = { dest, backStackEntry -> TopBar(dest, navController, backStackEntry) },
        bottomBar = { when (it.whichBottomBar) {
            SEARCH_BOTTOM_BAR -> SearchBottomBar(navController)
            STOCKS_BOTTOM_BAR -> StocksBottomBar(navController)
        }}
    ) {
        DestinationsNavHost(
            engine = engine,
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = Modifier.padding(it)
        )
    }
}

private val Destination.whichBottomBar get() = when (this) {
    CompanyScreenDestination, EtfsScreenDestination -> SEARCH_BOTTOM_BAR
    StocksScreenDestination, DividendsScreenDestination -> STOCKS_BOTTOM_BAR
}