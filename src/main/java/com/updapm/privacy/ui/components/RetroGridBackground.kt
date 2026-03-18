package com.updapm.privacy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.updapm.privacy.ui.theme.NeonColors

@Composable
fun RetroGridBackground(modifier: Modifier = Modifier) {
    // Simplified version - just a gradient background to avoid performance issues
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        NeonColors.DarkTechBackground,
                        Color(0xFF0F0F23),
                        NeonColors.DarkTechBackground
                    )
                )
            )
    )
}
