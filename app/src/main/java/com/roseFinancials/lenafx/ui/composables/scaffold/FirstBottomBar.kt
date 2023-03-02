package com.roseFinancials.lenafx.ui.composables.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import com.roseFinancials.lenafx.MainViewModel
import com.roseFinancials.lenafx.NavGraphs
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.core.viewmodel.activityViewModel
import com.roseFinancials.lenafx.destinations.BondsScreenDestination
import com.roseFinancials.lenafx.destinations.CompanyScreenDestination
import com.roseFinancials.lenafx.destinations.Destination
import com.roseFinancials.lenafx.destinations.IndexScreenDestination

@Composable
fun BottomBar(navController: NavHostController) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        FirstBottomBarItem.values().forEach { destination ->
            val isCurrentDestOnBackStack = navController.isRouteOnBackStack(destination.direction)
            val mainViewModel = activityViewModel<MainViewModel>()

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

                    mainViewModel.resetStocksState()

                    navController.navigate(destination.direction.route) {
                        popUpTo(NavGraphs.root) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        if (destination.text == "Bonds") ImageVector.vectorResource(R.drawable.card_membership)
                        else destination.icon,
                        contentDescription = destination.text
                    )
                },
                label = { Text(destination.text) },
            )
        }
    }
}

enum class FirstBottomBarItem(
    val direction: Destination,
    val icon: ImageVector,
    val text: String
) {
    Company(CompanyScreenDestination, Icons.Default.Search,"Company"),
    Index(IndexScreenDestination, Icons.Default.AccountBox, "Index"),
    Bonds(BondsScreenDestination, Icons.Default.AccountCircle, "Bonds")
}