package com.example.leadcapture.presentation.leadform.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppCircularLoader(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    strokeWidth: Dp = 3.dp
) {
    val transition = rememberInfiniteTransition(
        label = "circular_loader_transition"
    )

    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 900,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "circular_loader_rotation"
    )

    val color = MaterialTheme.colorScheme.primary

    Canvas(
        modifier = modifier.size(size)
    ) {
        val stroke = strokeWidth.toPx()
        val diameter = this.size.minDimension - stroke
        val topLeft = Offset(
            x = (this.size.width - diameter) / 2f,
            y = (this.size.height - diameter) / 2f
        )

        drawArc(
            color = color,
            startAngle = rotation,
            sweepAngle = 270f,
            useCenter = false,
            topLeft = topLeft,
            size = Size(diameter, diameter),
            style = Stroke(
                width = stroke,
                cap = StrokeCap.Round
            )
        )
    }
}