package com.example.leadcapture.core.ui.tokens

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object AppColors {

    val Primary = Color(0xFF596B3F)
    val OnPrimary = Color(0xFFFFFFFF)
    val PrimaryContainer = Color(0xFFE2E8D5)
    val OnPrimaryContainer = Color(0xFF28351D)

    // Light surfaces
    val LightBackground = Color(0xFFF5F3E8)
    val LightSurface = Color(0xFFFFFDF7)
    val LightSurfaceVariant = Color(0xFFE9E8DA)
    val LightOutline = Color(0xFFC8CCB8)
    val LightText = Color(0xFF252820)
    val LightSecondaryText = Color(0xFF686D60)
    val Error = Color(0xFFBA3030)

    val DarkPrimary = Color(0xFFFFC857)
    val DarkOnPrimary = Color(0xFF292016)
    val DarkBackground = Color(0xFF171717)
    val DarkSurface = Color(0xFF222222)
    val DarkSurfaceVariant = Color(0xFF303030)
    val DarkText = Color(0xFFF5F5F5)
    val DarkSecondaryText = Color(0xFFBDBDBD)
    val DarkOutline = Color(0xFF555555)
    val DarkPrimaryPressed = Color(0xFFE5AD38)
    val DarkPrimaryContainer = Color(0xFF5C4520)
    val DarkOnPrimaryContainer = Color(0xFFFFE4A3)
    val DarkError = Color(0xFFFF8A80)
}

/*val LightAppColorScheme = lightColorScheme(
    primary = AppColors.Primary,
    onPrimary = AppColors.OnPrimary,
    primaryContainer = AppColors.PrimaryContainer,
    onPrimaryContainer = AppColors.OnPrimaryContainer,
    background = AppColors.LightBackground,
    onBackground = AppColors.LightText,
    surface = AppColors.LightSurface,
    onSurface = AppColors.LightText,
    surfaceVariant = AppColors.LightSurfaceVariant,
    onSurfaceVariant = AppColors.LightSecondaryText,
    outline = AppColors.LightOutline,
    error = AppColors.Error
)

val DarkAppColorScheme = darkColorScheme(
    primary = Color(0xFFA5B4FC),
    onPrimary = Color(0xFF1E1B4B),
    primaryContainer = Color(0xFF312E81),
    onPrimaryContainer = Color(0xFFE0E7FF),
    background = AppColors.DarkBackground,
    onBackground = AppColors.DarkText,
    surface = AppColors.DarkSurface,
    onSurface = AppColors.DarkText,
    surfaceVariant = AppColors.DarkSurfaceVariant,
    onSurfaceVariant = AppColors.DarkSecondaryText,
    outline = AppColors.DarkOutline,
    error = AppColors.DarkError
)*/

val LightAppColorScheme = lightColorScheme(
    primary = AppColors.Primary,
    onPrimary = AppColors.OnPrimary,
    primaryContainer = AppColors.PrimaryContainer,
    onPrimaryContainer = AppColors.OnPrimaryContainer,

    background = AppColors.LightBackground,
    onBackground = AppColors.LightText,

    surface = AppColors.LightSurface,
    onSurface = AppColors.LightText,

    surfaceVariant = AppColors.LightSurfaceVariant,
    onSurfaceVariant = AppColors.LightSecondaryText,

    outline = AppColors.LightOutline,
    error = AppColors.Error,
    onError = Color.White
)

val DarkAppColorScheme = darkColorScheme(
    primary = AppColors.DarkPrimary,
    onPrimary = AppColors.DarkOnPrimary,
    primaryContainer = AppColors.DarkPrimaryContainer,
    onPrimaryContainer = AppColors.DarkOnPrimaryContainer,
    background = AppColors.DarkBackground,
    onBackground = AppColors.DarkText,
    surface = AppColors.DarkSurface,
    onSurface = AppColors.DarkText,
    surfaceVariant = AppColors.DarkSurfaceVariant,
    onSurfaceVariant = AppColors.DarkSecondaryText,
    outline = AppColors.DarkOutline,
    error = AppColors.DarkError
)
