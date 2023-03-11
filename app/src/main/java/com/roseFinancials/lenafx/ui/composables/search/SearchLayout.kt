package com.roseFinancials.lenafx.ui.composables.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.roseFinancials.lenafx.models.Quotes
import com.roseFinancials.lenafx.ui.composables.CustomTextField
import com.roseFinancials.lenafx.utils.LoadingState
import com.roseFinancials.lenafx.utils.searchBox

@Composable
fun SearchLayout(
    value: String,
    label: String,
    searchResults: List<Quotes>,
    ticker: String?,
    loadingState: LoadingState,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CustomTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        SearchBox(
            searchResults = searchResults,
            ticker = ticker,
            loadingState = loadingState,
            modifier = Modifier.searchBox(
                height = LocalConfiguration.current.screenHeightDp,
                borderColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.background
            ),
            onClick = onClick
        )
    }
}