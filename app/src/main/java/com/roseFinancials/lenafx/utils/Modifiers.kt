package com.roseFinancials.lenafx.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.searchBox(
    height: Int,
    borderColor: Color,
    backgroundColor: Color
) = this
    .height((height / 3).dp)
    .clip(RoundedCornerShape(4.dp))
    .border(
        border = BorderStroke(width = 1.dp, color = borderColor),
        shape = RoundedCornerShape(size = 4.dp)
    )
    .background(backgroundColor)