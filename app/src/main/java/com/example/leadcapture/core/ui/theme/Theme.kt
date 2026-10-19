package com.example.leadcapture.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.leadcapture.core.ui.tokens.DarkAppColorScheme
import com.example.leadcapture.core.ui.tokens.LightAppColorScheme

@Composable
fun LeadCaptureTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) {
            DarkAppColorScheme
        } else {
            LightAppColorScheme
        },
        typography = AppTypography,
        content = content
    )
}