package com.roseFinancials.lenafx.ui.composables.scaffold

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.navigation.popUpTo
import com.roseFinancials.lenafx.NavGraphs
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.destinations.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    destination: Destination,
    navController: NavHostController,
    previousBackStackEntry: NavBackStackEntry?
) {
    TopAppBar(
        navigationIcon = {
            NavigationIconUtil(
                destination = destination,
                navBackStackEntry = previousBackStackEntry,
                onClick = {
                    navController.navigate(previousBackStackEntry?.destination?.route!!) {
                        popUpTo(NavGraphs.root) {
                            saveState = false
                        }
                        restoreState = false
                    }
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