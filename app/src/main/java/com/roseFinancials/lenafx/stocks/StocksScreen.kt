@file:Suppress("DuplicatedCode")

package com.roseFinancials.lenafx.stocks

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.popUpTo
import com.roseFinancials.lenafx.NavGraphs
import com.roseFinancials.lenafx.utils.LoadingState
import com.roseFinancials.lenafx.utils.searchBox

@Destination
@Composable
fun StocksScreen(
    navController: NavController,
    viewModel: StocksViewModel = hiltViewModel()
) {
    val stocks = viewModel.stocksState.collectAsState().value
    val apiState = viewModel.apiState.collectAsState().value
    val tickerState = viewModel.tickerState.collectAsState().value
    val indexState = viewModel.indexState.collectAsState().value
    val loadingState = viewModel.loadingState.collectAsState().value
    val backStackEntry = navController.previousBackStackEntry

    LaunchedEffect(Unit) { if (apiState) {
        viewModel.updateApiState(false)
        viewModel.callApis()
    }}

    BackHandler {
        viewModel.onBackPressed()
        backStackEntry?.let { navController.navigate(backStackEntry.destination.route!!) {
            popUpTo(NavGraphs.root) {
                saveState = false
            }
            restoreState = false
        }} ?: navController.popBackStack()
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
    ) {
        Text("${stocks.ticker}", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)

        Spacer(Modifier.height(20.dp))

        if (loadingState == LoadingState.LOADING) CircularProgressIndicator()
        else {
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.searchBox(
                    height = LocalConfiguration.current.screenHeightDp,
                    borderColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.background
                )
            ) {
                items(tickerState) { result ->
                    Text("${result.date.uppercase()}: ${result.close}")
                }
            }

            Spacer(Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.searchBox(
                    height = LocalConfiguration.current.screenHeightDp,
                    borderColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.background
                )
            ) {
                items(indexState) { result ->
                    Text("${result.date.uppercase()}: ${result.close}")
                }
            }
        }
    }
}