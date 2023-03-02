package com.roseFinancials.lenafx.ui.composables.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.roseFinancials.lenafx.MainViewModel
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.core.viewmodel.activityViewModel
import com.roseFinancials.lenafx.destinations.Destination
import com.roseFinancials.lenafx.destinations.StocksScreenDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    destination: Destination,
    navController: NavHostController
) {
    val mainViewModel = activityViewModel<MainViewModel>()
    TopAppBar(
        navigationIcon = {
            if (destination is StocksScreenDestination) {
                Button(onClick = {
                    mainViewModel.resetStocksState()
                    navController.popBackStack()
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