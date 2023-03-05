package com.roseFinancials.lenafx.ui.composables.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.navigation.popUpTo
import com.roseFinancials.lenafx.NavGraphs
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.destinations.Destination
import com.roseFinancials.lenafx.destinations.StocksScreenDestination
import com.roseFinancials.lenafx.stocks.StocksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    destination: Destination,
    navController: NavHostController,
    previousBackStackEntry: NavBackStackEntry?
) {
    TopAppBar(
        navigationIcon = {
            destination.TopBarNavigationButton(
                navBackStackEntry = previousBackStackEntry,
                button = {
                    navController.navigate(previousBackStackEntry?.destination?.route!!) {
                        popUpTo(NavGraphs.root) {
                            saveState = false
                        }
                        restoreState = false
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Previous Page"
                    )
                }
            )
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

@Composable
fun Destination.TopBarNavigationButton(
    navBackStackEntry: NavBackStackEntry?,
    button: () -> Unit,
    icon: @Composable () -> Unit
) {
    return when (this) {
        StocksScreenDestination -> {
            val viewModel = navBackStackEntry?.let { hiltViewModel<StocksViewModel>(navBackStackEntry) }
            IconButton(onClick = {
                viewModel?.resetState()
                button()
            }) { icon() }
        }
        else -> return
    }
}