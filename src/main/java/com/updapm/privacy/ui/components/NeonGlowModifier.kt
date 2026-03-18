package com.updapm.privacy.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.neonGlow(
    glowColor: Color,
    borderRadius: Dp = 16.dp,
    glowRadius: Dp = 8.dp,
    animated: Boolean = true
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )
    
    this
        .shadow(
            elevation = glowRadius,
            shape = RoundedCornerShape(borderRadius),
            ambientColor = glowColor.copy(alpha = if (animated) alpha else 0.7f),
            spotColor = glowColor.copy(alpha = if (animated) alpha else 0.7f)
        )
        .border(
            width = 1.dp,
            color = glowColor.copy(alpha = if (animated) alpha else 0.8f),
            shape = RoundedCornerShape(borderRadius)
        )
}

fun Modifier.neonBorder(
    color: Color,
    width: Dp = 2.dp,
    cornerRadius: Dp = 16.dp
): Modifier = this.border(
    width = width,
    color = color,
    shape = RoundedCornerShape(cornerRadius)
)

fun Modifier.pulseGlow(
    color: Color,
    maxRadius: Dp = 20.dp
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val radius by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = maxRadius.value,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulseRadius"
    )
    
    this.drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                this.color = color.copy(alpha = 1f - (radius / maxRadius.value))
            }
            canvas.drawCircle(
                center = center,
                radius = radius,
                paint = paint
            )
        }
    }
}
