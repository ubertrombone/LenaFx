package com.roseFinancials.lenafx.ui.composables.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.spec.Route
import com.roseFinancials.lenafx.appCurrentDestinationAsState
import com.roseFinancials.lenafx.destinations.Destination
import com.roseFinancials.lenafx.startAppDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScaffold(
    startRoute: Route,
    navController: NavHostController,
    topBar: @Composable (Destination) -> Unit,
    bottomBar: @Composable (Destination) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val destination = navController.appCurrentDestinationAsState().value ?: startRoute.startAppDestination
    val navBackStackEntry = navController.currentBackStackEntry

    // 👇 only for debugging, you shouldn't use currentBackStack API as it is restricted by annotation
    navController.currentBackStack.collectAsState().value.print()

    Scaffold(
        topBar = { topBar(destination) },
        bottomBar = { bottomBar(destination) },
        content = content
    )
}

private fun List<NavBackStackEntry>.print(prefix: String = "stack") {
    val stack = map { it.destination.route }.toTypedArray().contentToString()
    println("$prefix = $stack")
}