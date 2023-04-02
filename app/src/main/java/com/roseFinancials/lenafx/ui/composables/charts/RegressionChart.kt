package com.roseFinancials.lenafx.ui.composables.charts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.point.cofig.PointType
import com.roseFinancials.lenafx.charty.common.label.XLabels
import com.roseFinancials.lenafx.charty.common.label.YLabels
import com.roseFinancials.lenafx.charty.linearregression.LinearRegressionChart
import com.roseFinancials.lenafx.charty.linearregression.config.LinearRegressionConfig
import com.roseFinancials.lenafx.charty.linearregression.model.LinearRegressionData

@Composable
fun RegressionChart(
    data: List<LinearRegressionData>,
    modifier: Modifier = Modifier
) {
    LinearRegressionChart(
        modifier = modifier.fillMaxWidth()
            .height(300.dp)
            .padding(20.dp),
        linearRegressionData = data,
        scatterColor = MaterialTheme.colorScheme.secondary,
        lineColor = MaterialTheme.colorScheme.primary,
        linearRegressionConfig = LinearRegressionConfig(
            pointType = PointType.Fill,
            pointSize = 3.dp
        ),
        yLabelConfig = YLabels(
            fontColor = MaterialTheme.colorScheme.onBackground,
            rotation = -45f,
            lineStartPadding = 10f
        ),
        xLabelConfig = XLabels(
            fontColor = MaterialTheme.colorScheme.onBackground,
            rotation = 30f
        )
    )
}