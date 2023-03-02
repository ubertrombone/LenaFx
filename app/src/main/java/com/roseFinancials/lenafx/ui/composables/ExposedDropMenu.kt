package com.roseFinancials.lenafx.ui.composables

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropMenu(
    menuContent: List<Pair<String, String>>,
    text: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(menuContent[0].second) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        CustomTextField(
            value = selectedOption,
            onValueChange = {  },
            readOnly = true,
            label = { Text(text = text) },
            leadingIcon = { Icon(Icons.Default.AccountBox, contentDescription = null) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            menuContent.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = "${item.second} - ${item.first}") },
                    colors = MenuDefaults.itemColors(textColor = MaterialTheme.colorScheme.onBackground),
                    onClick = {
                        selectedOption = item.second
                        onClick(item.first)
                        expanded = false
                    }
                )
            }
        }
    }
}