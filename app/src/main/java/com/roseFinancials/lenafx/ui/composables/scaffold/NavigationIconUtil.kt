package com.roseFinancials.lenafx.ui.composables.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.roseFinancials.lenafx.destinations.Destination
import com.roseFinancials.lenafx.destinations.DividendsScreenDestination
import com.roseFinancials.lenafx.destinations.StocksScreenDestination
import com.roseFinancials.lenafx.stocks.DividendsViewModel
import com.roseFinancials.lenafx.stocks.StocksViewModel
import com.roseFinancials.lenafx.utils.ResetViewModel

@Composable
fun NavigationIconUtil(
    destination: Destination,
    navBackStackEntry: NavBackStackEntry?,
    onClick: () -> Unit
) {
    val viewModel = destination.getViewModelInstance(navBackStackEntry)
    viewModel?.let {
        IconButton(onClick = {
            viewModel.resetState()
            viewModel.updateApiState(true)
            viewModel.updateDividendsState(false)
            onClick()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Previous Page"
            )
        }
    }
}

@Composable
fun Destination.getViewModelInstance(
    navBackStackEntry: NavBackStackEntry?
): ResetViewModel? {
    return when (this) {
        StocksScreenDestination -> {
            navBackStackEntry?.let { hiltViewModel<StocksViewModel>(navBackStackEntry) }
                ?: throw NullPointerException("Screen has no backstack")
        }
        DividendsScreenDestination -> {
            navBackStackEntry?.let { hiltViewModel<DividendsViewModel>(navBackStackEntry) }
                ?: throw NullPointerException("Screen has no backstack")
        }
        else -> null
    }
}