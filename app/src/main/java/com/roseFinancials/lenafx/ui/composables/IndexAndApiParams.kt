package com.roseFinancials.lenafx.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.roseFinancials.lenafx.R

@Composable
fun IndexAndApiParams(
    selectedRange: String,
    selectedInterval: String,
    menuContent: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
    indexClick: (String) -> Unit,
    rangeClick: (String) -> Unit,
    intervalClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        ExposedDropMenu(
            menuContent = menuContent,
            text = stringResource(R.string.index_name),
            modifier = Modifier.fillMaxWidth(),
            onClick = { indexClick(it) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        RangesAndIntervals(
            selectedRange = selectedRange,
            selectedInterval = selectedInterval,
            rangeClick = rangeClick,
            intervalClick = intervalClick
        )
    }
}