package com.roseFinancials.lenafx.ui.composables.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.navigation.popUpTo
import com.roseFinancials.lenafx.NavGraphs
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.destinations.Destination
import com.roseFinancials.lenafx.destinations.StocksScreenDestination
import com.roseFinancials.lenafx.stocks.presentation.StocksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    destination: Destination,
    navController: NavHostController
) {
    TopAppBar(
        navigationIcon = {
            if (destination is StocksScreenDestination) {
                // Gets the entry we want to navigate to.
                val backStackEntry = navController.previousBackStackEntry
                // Gets NavBackStackEntry in order to instantiate StocksViewModel so state can be reset on back press.
                val currentDestination = remember(navController.currentBackStackEntry) {
                    navController.getBackStackEntry(destination.route)
                }
                val stocksViewModel = hiltViewModel<StocksViewModel>(currentDestination)
                Button(onClick = {
                    backStackEntry?.let {
                        stocksViewModel.resetState()
                        navController.navigate(backStackEntry.destination.route!!) {
                        popUpTo(NavGraphs.root) {
                            saveState = false
                        }
                        restoreState = false
                        }
                    } ?: navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Previous Page"
                    )
                }
            }
        },
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}