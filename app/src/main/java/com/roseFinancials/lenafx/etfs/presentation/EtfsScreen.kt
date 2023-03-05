package com.roseFinancials.lenafx.etfs.presentation

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.ui.composables.ContinueButton
import com.roseFinancials.lenafx.ui.composables.RangesAndIntervals
import com.roseFinancials.lenafx.ui.composables.search.SearchLayout

@Destination
@Composable
fun EtfsScreen(
    navController: NavController,
    viewModel: EtfsViewModel = hiltViewModel()
) {
    val etfDataState by viewModel.etfDataStateFlow.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()

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
                value = viewModel.searchQuery.collectAsState().value,
                label = stringResource(R.string.search_etfs),
                searchResults = viewModel.searchResults.collectAsState().value,
                ticker = etfDataState.ticker,
                loadingState = loadingState,
                onValueChange = viewModel::updateSearchQuery,
                onClick = viewModel::updateTicker
            )

            Spacer(Modifier.height(20.dp))

            RangesAndIntervals(
                selectedRange = etfDataState.dateRange,
                selectedInterval = etfDataState.interval,
                rangeClick = viewModel::updateRange,
                intervalClick = viewModel::updateInterval
            )
        }

        ContinueButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            enabled = !etfDataState.ticker.isNullOrBlank()
        ) {
//            if (isCurrentDestOnBackStack) {
//                navController.popBackStack(DataScreenDestination, false)
//                return@ContinueButton
//            }

            //navController.navigate(DataScreenDestination.route)
        }
    }
}