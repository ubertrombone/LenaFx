package com.roseFinancials.lenafx.bonds.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.roseFinancials.lenafx.MainViewModel
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.core.viewmodel.activityViewModel
import com.roseFinancials.lenafx.core.viewmodel.viewModel
import com.roseFinancials.lenafx.ui.composables.ContinueButton
import com.roseFinancials.lenafx.ui.composables.RangesAndIntervals
import com.roseFinancials.lenafx.ui.composables.search.SearchLayout

@Destination
@Composable
fun BondsScreen(
    navController: NavController,
    viewModel: BondsViewModel = viewModel()
) {
    val mainViewModel = activityViewModel<MainViewModel>()
    val bondDataState by viewModel.bondDataStateFlow.collectAsState()
    val loadingState by mainViewModel.loadingState.collectAsState()
    //val isCurrentDestOnBackStack = navController.isRouteOnBackStack()

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SearchLayout(
                value = mainViewModel.searchQuery.collectAsState().value,
                label = stringResource(R.string.search_bonds),
                searchResults = mainViewModel.searchResults.collectAsState().value,
                ticker = bondDataState.ticker,
                loadingState = loadingState,
                onValueChange = mainViewModel::updateSearchQuery,
                onClick = viewModel::updateTicker
            )

            Spacer(Modifier.height(20.dp))

            RangesAndIntervals(
                selectedRange = bondDataState.dateRange,
                selectedInterval = bondDataState.interval,
                rangeClick = viewModel::updateRange,
                intervalClick = viewModel::updateInterval
            )
        }

        ContinueButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            enabled = !bondDataState.ticker.isNullOrBlank()
        ) {
//            if (isCurrentDestOnBackStack) {
//                navController.popBackStack(DataScreenDestination, false)
//                return@ContinueButton
//            }

            //navController.navigate(DataScreenDestination.route)
        }
    }
}