package com.updapm.privacy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Neon Color Palette
object NeonColors {
    val NeonBlue = Color(0xFF00F3FF)
    val CyberPurple = Color(0xFF8A2BE2)
    val MagentaGlow = Color(0xFFFF00FF)
    val SynthwavePink = Color(0xFFFF007F)
    val ElectricCyan = Color(0xFF00FFFF)
    val DarkTechBackground = Color(0xFF0A0A19)
    val DarkCard = Color(0xFF1A1A2E)
    val NeonRed = Color(0xFFFF0055)
    val NeonGreen = Color(0xFF00FF88)
    val GridGray = Color(0xFF2A2A3E)
}

private val NeonColorScheme = darkColorScheme(
    primary = NeonColors.NeonBlue,
    secondary = NeonColors.CyberPurple,
    tertiary = NeonColors.MagentaGlow,
    background = NeonColors.DarkTechBackground,
    surface = NeonColors.DarkCard,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = NeonColors.ElectricCyan,
    onSurface = Color.White
)

@Composable
fun NeonTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = NeonColorScheme,
        typography = NeonTypography,
        content = content
    )
}
