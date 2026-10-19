package com.example.leadcapture.presentation.leadform.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.leadcapture.core.ui.tokens.AppGradients

@Composable
fun AnimatedGradientCard(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()

    val color1 = if (isDarkTheme) {
        AppGradients.DarkGradient1
    } else {
        AppGradients.LightGradient1
    }

    val color2 = if (isDarkTheme) {
        AppGradients.DarkGradient2
    } else {
        AppGradients.LightGradient2
    }

    val color3 = if (isDarkTheme) {
        AppGradients.DarkGradient3
    } else {
        AppGradients.LightGradient3
    }

    val infiniteTransition = rememberInfiniteTransition(
        label = "cardGradientTransition"
    )

    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 10000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "gradientProgress"
    )

    val startX = 0.2f + animationProgress * 0.8f
    val startY = animationProgress
    val endX = 1f - animationProgress * 0.7f
    val endY = 1f - animationProgress * 0.4f

    val gradient = Brush.linearGradient(
        colors = listOf(color1, color2, color3),
        start = Offset(startX * 1000f, startY * 1000f),
        end = Offset(endX * 1000f, endY * 1000f)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(gradient),
        content = content
    )

}