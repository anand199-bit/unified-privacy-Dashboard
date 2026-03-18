package com.updapm.privacy.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.updapm.privacy.ui.theme.NeonColors
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun NeonGauge(
    score: Int,
    maxScore: Int = 100,
    modifier: Modifier = Modifier
) {
    var animatedScore by remember { mutableStateOf(0f) }
    
    LaunchedEffect(score) {
        animate(
            initialValue = animatedScore,
            targetValue = score.toFloat(),
            animationSpec = tween(1500, easing = FastOutSlowInEasing)
        ) { value, _ ->
            animatedScore = value
        }
    }
    
    val infiniteTransition = rememberInfiniteTransition(label = "gaugeGlow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )
    
    Box(
        modifier = modifier.size(250.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasSize = size.minDimension
            val radius = canvasSize / 2.5f
            val center = Offset(size.width / 2, size.height / 2)
            val strokeWidth = 20f
            
            // Background arc
            drawArc(
                color = NeonColors.GridGray.copy(alpha = 0.3f),
                startAngle = 135f,
                sweepAngle = 270f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
            
            // Animated progress arc with gradient
            val sweepAngle = (animatedScore / maxScore) * 270f
            val gradient = Brush.sweepGradient(
                colors = listOf(
                    NeonColors.NeonBlue,
                    NeonColors.ElectricCyan,
                    NeonColors.CyberPurple,
                    NeonColors.MagentaGlow
                ),
                center = center
            )
            
            drawArc(
                brush = gradient,
                startAngle = 135f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                alpha = glowAlpha
            )
            
            // Outer glow ring
            drawArc(
                brush = gradient,
                startAngle = 135f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(center.x - radius - 10, center.y - radius - 10),
                size = Size(radius * 2 + 20, radius * 2 + 20),
                style = Stroke(width = 5f, cap = StrokeCap.Round),
                alpha = glowAlpha * 0.3f
            )
        }
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${animatedScore.toInt()}",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = NeonColors.ElectricCyan
            )
            Text(
                text = "PRIVACY SCORE",
                style = MaterialTheme.typography.labelLarge,
                color = NeonColors.NeonBlue.copy(alpha = 0.8f)
            )
        }
    }
}
