package com.roseFinancials.lenafx.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.roseFinancials.lenafx.utils.Constants.INTERVALS
import com.roseFinancials.lenafx.utils.Constants.RANGES

@Composable
fun RangesAndIntervals(
    selectedRange: String,
    selectedInterval: String,
    modifier: Modifier = Modifier,
    rangeClick: (String) -> Unit,
    intervalClick: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        DropMenu(
            menuContent = RANGES,
            text = "Ranges",
            selected = selectedRange,
            onClick = { rangeClick(it) }
        )

        DropMenu(
            menuContent = INTERVALS,
            text = "Intervals",
            selected = selectedInterval,
            onClick = { intervalClick(it) }
        )
    }
}