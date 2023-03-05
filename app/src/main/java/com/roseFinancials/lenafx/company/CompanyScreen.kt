package com.roseFinancials.lenafx.company

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
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.destinations.StocksScreenDestination
import com.roseFinancials.lenafx.ui.composables.ContinueButton
import com.roseFinancials.lenafx.ui.composables.IndexAndApiParams
import com.roseFinancials.lenafx.ui.composables.search.SearchLayout
import com.roseFinancials.lenafx.utils.Constants.INDICES

@RootNavGraph(start = true)
@Destination
@Composable
fun CompanyScreen(
    navController: NavController,
    viewModel: CompanyViewModel = hiltViewModel()
) {
    val stocksState by viewModel.stocksStateFlow.collectAsState()
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchLayout(
                value = viewModel.searchQuery.collectAsState().value,
                label = stringResource(R.string.search_company),
                searchResults = viewModel.searchResults.collectAsState().value,
                ticker = stocksState.ticker,
                loadingState = loadingState,
                onValueChange = viewModel::updateSearchQuery,
                onClick = viewModel::updateTicker
            )

            Spacer(modifier = Modifier.height(20.dp))

            IndexAndApiParams(
                selectedRange = stocksState.dateRange,
                selectedInterval = stocksState.interval,
                menuContent = INDICES,
                indexClick = viewModel::updateIndex,
                rangeClick = viewModel::updateRange,
                intervalClick = viewModel::updateInterval
            )
        }

        ContinueButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            enabled = !stocksState.ticker.isNullOrBlank()
        ) { navController.navigate(StocksScreenDestination.route) }
    }
}