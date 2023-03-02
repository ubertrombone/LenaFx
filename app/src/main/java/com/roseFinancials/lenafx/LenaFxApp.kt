package com.roseFinancials.lenafx

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.roseFinancials.lenafx.destinations.*
import com.roseFinancials.lenafx.ui.composables.scaffold.BottomBar
import com.roseFinancials.lenafx.ui.composables.scaffold.FirstScaffold
import com.roseFinancials.lenafx.ui.composables.scaffold.TopBar

@Composable
fun LenaFxApp() {
    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    FirstScaffold(
        startRoute = NavGraphs.root.startRoute,
        navController = navController,
        topBar = { dest -> TopBar(dest, navController) },
        bottomBar = { if (it.shouldShowBottomBar) BottomBar(navController) }
    ) {
        DestinationsNavHost(
            engine = engine,
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = Modifier.padding(it)
        )
    }
}

private val Destination.shouldShowBottomBar get() = this !is StocksScreenDestination

// TODO: When SecondBottomBar is ready, move this into the lambda
private val Destination.whichBottomBar get() = when (this) {
    IndexScreenDestination, CompanyScreenDestination, BondsScreenDestination -> true
    else -> false
}