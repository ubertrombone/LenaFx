package com.roseFinancials.lenafx.ui.composables.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.roseFinancials.lenafx.R
import com.roseFinancials.lenafx.models.Quotes
import com.roseFinancials.lenafx.utils.LoadingState

@Composable
fun SearchBox(
    searchResults: List<Quotes>,
    ticker: String?,
    modifier: Modifier = Modifier,
    loadingState: LoadingState,
    onClick: (String) -> Unit
) {
    if (searchResults.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            if (loadingState == LoadingState.LOADING) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = stringResource(R.string.no_results),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            items(searchResults) { result ->
                CompanyCard(
                    company = result.shortname,
                    ticker = result.symbol,
                    exchange = result.exchDisp,
                    selected = ticker == result.symbol,
                    onClick = { onClick(it) }
                )
            }
        }
    }
}