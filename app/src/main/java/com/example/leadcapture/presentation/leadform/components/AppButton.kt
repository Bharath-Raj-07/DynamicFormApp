package com.example.leadcapture.presentation.leadform.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.leadcapture.core.ui.tokens.AppShapes
import com.example.leadcapture.core.ui.tokens.AppSpacing

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 52.dp),
        shape = AppShapes.Button,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor =
                MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor =
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        if (isLoading) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppSpacing.Medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppCircularLoader(
                    size = 20.dp,
                    strokeWidth = 2.5.dp
                )
                Text(
                    text = "Please wait…",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}