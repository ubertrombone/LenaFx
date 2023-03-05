package com.roseFinancials.lenafx.ui.composables.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.destinations.Destination
import com.roseFinancials.lenafx.destinations.DividendsScreenDestination
import com.roseFinancials.lenafx.destinations.StocksScreenDestination

@Composable
fun StocksBottomBar(navController: NavHostController) {
    val previousBackStackEntry = navController.previousBackStackEntry?.destination?.route
        ?: throw NullPointerException("No previous backstack entry")

    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        SecondBottomBarItem.values().forEach { destination ->
            val isCurrentDestOnBackStack = navController.isRouteOnBackStack(destination.direction)

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary
                ),
                selected = isCurrentDestOnBackStack,
                onClick = {
                    if (isCurrentDestOnBackStack) {
                        navController.popBackStack(destination.direction, false)
                        return@NavigationBarItem
                    }
                    navController.navigate(destination.direction.route) {
                        popUpTo(previousBackStackEntry) {
                            saveState = false
                        }
                        launchSingleTop = true
                        restoreState = false
                    }
                },
                icon = {
                    Icon(
                        when (destination.text) {
                            "Stocks" -> ImageVector.vectorResource(R.drawable.insights)
                            "Dividends" -> ImageVector.vectorResource(R.drawable.price_check)
                            else -> Icons.Default.Search
                        },
                        contentDescription = destination.text
                    )
                },
                label = { Text(destination.text) },
            )
        }
    }
}

enum class SecondBottomBarItem(
    val direction: Destination,
    val text: String
) {
    Stocks(StocksScreenDestination, "Stocks"),
    Dividends(DividendsScreenDestination, "Dividends")
}