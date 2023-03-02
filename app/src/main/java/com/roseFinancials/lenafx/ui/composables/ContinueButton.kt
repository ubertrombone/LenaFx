package com.roseFinancials.lenafx.ui.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.roseFinancials.lenafx.R

@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    buttonClick: () -> Unit
) {
    Button(
        onClick = buttonClick,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorScheme.primary,
            contentColor = colorScheme.onPrimary,
            disabledContainerColor = if (isSystemInDarkTheme()) colorScheme.secondary else colorScheme.secondaryContainer,
            disabledContentColor = if (isSystemInDarkTheme()) colorScheme.onBackground else colorScheme.background
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.continue_button),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Top)
            )

            Spacer(Modifier.width(8.dp))

            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null)
        }
    }
}