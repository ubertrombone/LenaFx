package com.roseFinancials.lenafx.company.presentation

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
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import com.roseFinancials.lenafx.MainViewModel
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.core.viewmodel.activityViewModel
import com.roseFinancials.lenafx.core.viewmodel.viewModel
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
    viewModel: CompanyViewModel = viewModel()
) {
    val mainViewModel = activityViewModel<MainViewModel>()
    val stocksState by viewModel.stocksStateFlow.collectAsState()
    val loadingState by mainViewModel.loadingState.collectAsState()
    val isCurrentDestOnBackStack = navController.isRouteOnBackStack(StocksScreenDestination)

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
                value = mainViewModel.searchQuery.collectAsState().value,
                label = stringResource(R.string.search_company),
                searchResults = mainViewModel.searchResults.collectAsState().value,
                ticker = stocksState.ticker,
                loadingState = loadingState,
                onValueChange = mainViewModel::updateSearchQuery,
                onClick = mainViewModel::updateTickerValue
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
        ) {
            if (isCurrentDestOnBackStack) {
                navController.popBackStack(StocksScreenDestination, false)
                return@ContinueButton
            }

            navController.navigate(StocksScreenDestination.route)
        }
    }
}