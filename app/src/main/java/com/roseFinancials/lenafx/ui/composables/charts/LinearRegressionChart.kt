package com.roseFinancials.lenafx.ui.composables.charts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.point.PointChart
import com.himanshoe.charty.point.cofig.PointConfig
import com.himanshoe.charty.point.cofig.PointType
import com.himanshoe.charty.point.model.PointData

@Composable
fun LinearRegressionChart(
    data: List<PointData>,
    modifier: Modifier = Modifier
) {
    PointChart(
        pointData = data,
        color = MaterialTheme.colorScheme.primary,
        pointConfig = PointConfig(pointType = PointType.Fill),
        modifier = modifier.fillMaxWidth().height(300.dp).padding(20.dp)
    )
}