package com.updapm.privacy.presentation.risk

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.updapm.privacy.ui.components.*
import com.updapm.privacy.ui.theme.NeonColors
import com.updapm.privacy.ui.theme.NeonGradients

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiskAnalysisScreen(
    onBack: () -> Unit,
    viewModel: RiskAnalysisViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "AI RISK ANALYZER",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = NeonColors.CyberPurple)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NeonColors.DarkTechBackground,
                    titleContentColor = NeonColors.CyberPurple
                )
            )
        },
        containerColor = NeonColors.DarkTechBackground
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            RetroGridBackground()
            
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        NeonGauge(score = uiState.overallRiskScore)
                    }
                }
                
                item {
                    RiskLevelCard(
                        riskLevel = uiState.riskLevel,
                        score = uiState.overallRiskScore
                    )
                }
                
                item {
                    Text(
                        text = "AI RECOMMENDATIONS",
                        style = MaterialTheme.typography.titleLarge,
                        color = NeonColors.MagentaGlow,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                
                items(uiState.recommendations) { recommendation ->
                    RecommendationCard(recommendation)
                }
                
                item {
                    Text(
                        text = "APP RISK BREAKDOWN",
                        style = MaterialTheme.typography.titleLarge,
                        color = NeonColors.MagentaGlow,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                
                items(uiState.appRisks) { appRisk ->
                    AppRiskCard(appRisk)
                }
            }
        }
    }
}

@Composable
private fun RiskLevelCard(riskLevel: String, score: Int) {
    val (color, gradient) = when (riskLevel) {
        "HIGH" -> NeonColors.NeonRed to NeonGradients.RiskGradientHigh
        "MEDIUM" -> NeonColors.SynthwavePink to NeonGradients.RiskGradientMedium
        else -> NeonColors.NeonGreen to NeonGradients.RiskGradientSafe
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(glowColor = color, borderRadius = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient)
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "RISK LEVEL: $riskLevel",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
                Text(
                    text = "Privacy Score: $score/100",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
private fun RecommendationCard(recommendation: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(glowColor = NeonColors.ElectricCyan, borderRadius = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeonColors.DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Lightbulb,
                contentDescription = null,
                tint = NeonColors.ElectricCyan,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = recommendation,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Composable
private fun AppRiskCard(appRisk: AppRiskInfo) {
    val riskColor = when (appRisk.riskLevel) {
        "HIGH" -> NeonColors.NeonRed
        "MEDIUM" -> NeonColors.SynthwavePink
        else -> NeonColors.NeonGreen
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(glowColor = riskColor, borderRadius = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeonColors.DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = appRisk.appName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
                Text(
                    text = "${appRisk.permissionCount} permissions",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.6f)
                )
            }
            
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${appRisk.riskScore}",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = riskColor
                )
                Surface(
                    color = riskColor.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = appRisk.riskLevel,
                        style = MaterialTheme.typography.labelSmall,
                        color = riskColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}
