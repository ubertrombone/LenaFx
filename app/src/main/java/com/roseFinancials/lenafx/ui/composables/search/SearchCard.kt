package com.roseFinancials.lenafx.ui.composables.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyCard(
    company: String,
    ticker: String,
    market: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(0.dp),
        onClick = { onClick(ticker) },
        colors = CardDefaults.cardColors(
            containerColor = if (selected) colorScheme.secondary else colorScheme.background,
            contentColor = if (selected) colorScheme.onSecondary else colorScheme.onBackground
        ),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxHeight().padding(5.dp)
            ) {
                Text(
                    text = ticker,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (company.length > 25) "${company.dropLast(company.length - 25).trimEnd()}..." else company,
                    fontSize = 13.sp,
                    maxLines = 1
                )
            }
            Text(text = market, fontSize = 16.sp, modifier = Modifier.padding(5.dp))
        }
    }
}