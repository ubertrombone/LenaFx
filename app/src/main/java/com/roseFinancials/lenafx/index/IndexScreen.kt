package com.roseFinancials.lenafx.index

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.popBackStack
import com.ramcosta.composedestinations.utils.isRouteOnBackStack
import com.roseFinancials.lenafx.MainViewModel
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.core.viewmodel.activityViewModel
import com.roseFinancials.lenafx.core.viewmodel.viewModel
import com.roseFinancials.lenafx.destinations.StocksScreenDestination
import com.roseFinancials.lenafx.ui.composables.ContinueButton
import com.roseFinancials.lenafx.ui.composables.CustomTextField
import com.roseFinancials.lenafx.ui.composables.IndexAndApiParams
import com.roseFinancials.lenafx.utils.Constants.INDICES

@Destination
@Composable
fun IndexScreen(
    navController: NavController,
    viewModel: IndexViewModel = viewModel()
) {
    val mainViewModel = activityViewModel<MainViewModel>()
    val stocksState by viewModel.stocksStateFlow.collectAsState()
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomTextField(
                value = mainViewModel.tickerValue.collectAsState().value,
                onValueChange = mainViewModel::updateTickerValue,
                label = { Text(text = stringResource(R.string.enter_ticker)) },
                leadingIcon = { Icon(painterResource(R.drawable.trending_up), contentDescription = null) }
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