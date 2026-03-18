package com.updapm.privacy.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object NeonGradients {
    val BlueCyan = Brush.linearGradient(
        colors = listOf(NeonColors.NeonBlue, NeonColors.ElectricCyan),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )
    
    val PurpleMagenta = Brush.linearGradient(
        colors = listOf(NeonColors.CyberPurple, NeonColors.MagentaGlow),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )
    
    val PinkPurple = Brush.linearGradient(
        colors = listOf(NeonColors.SynthwavePink, NeonColors.CyberPurple),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )
    
    val CyberBackground = Brush.verticalGradient(
        colors = listOf(
            NeonColors.DarkTechBackground,
            Color(0xFF0F0F23),
            NeonColors.DarkTechBackground
        )
    )
    
    val CardGradient = Brush.linearGradient(
        colors = listOf(
            NeonColors.DarkCard.copy(alpha = 0.8f),
            NeonColors.DarkCard.copy(alpha = 0.6f)
        )
    )
    
    val RiskGradientSafe = Brush.horizontalGradient(
        colors = listOf(NeonColors.ElectricCyan, NeonColors.NeonGreen)
    )
    
    val RiskGradientMedium = Brush.horizontalGradient(
        colors = listOf(NeonColors.CyberPurple, NeonColors.MagentaGlow)
    )
    
    val RiskGradientHigh = Brush.horizontalGradient(
        colors = listOf(NeonColors.NeonRed, NeonColors.SynthwavePink)
    )
}
