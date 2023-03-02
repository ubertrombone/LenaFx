package com.roseFinancials.lenafx.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropMenu(
    menuContent: List<String>,
    text: String,
    selected: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(4.dp),
        onClick = { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.primary,
            contentColor = colorScheme.onPrimary
        ),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(10.dp, 5.dp)
        ) {
            Text(text = text)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(color = colorScheme.secondary)
            ) {
                menuContent.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        leadingIcon = { if (selected == item) Icon(imageVector = Icons.Default.Done, contentDescription = null) },
                        colors = MenuDefaults.itemColors(
                            textColor = colorScheme.onSecondary,
                            leadingIconColor = colorScheme.onSecondary,
                        ),
                        onClick = {
                            expanded = false
                            onClick(item)
                        }
                    )
                }
            }
        }
    }
}