package com.updapm.privacy.presentation.dashboard

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DashboardScreen(
    onNavigateToPermissions: () -> Unit,
    onNavigateToRisk: () -> Unit,
    onNavigateToLogs: () -> Unit,
    onNavigateToParental: () -> Unit,
    onNavigateToCompliance: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    

    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NeonColors.DarkTechBackground),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                // Header
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "UPDA",
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = NeonColors.ElectricCyan
                        )
                        Text(
                            text = "UNIFIED PRIVACY DASHBOARD",
                            style = MaterialTheme.typography.labelLarge,
                            color = NeonColors.NeonBlue.copy(alpha = 0.7f)
                        )
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            
            // Privacy Score Gauge
            item {
                AnimatedVisibility(
                    visible = !uiState.isLoading,
                    enter = fadeIn() + scaleIn()
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        NeonGauge(score = uiState.privacyScore)
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            
            // Stats Row
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatCard(
                        title = "APPS",
                        value = uiState.totalApps.toString(),
                        color = NeonColors.NeonBlue
                    )
                    StatCard(
                        title = "HIGH RISK",
                        value = uiState.highRiskApps.toString(),
                        color = NeonColors.NeonRed
                    )
                }
            }
            
            item {
                Text(
                    text = "CONTROL CENTER",
                    style = MaterialTheme.typography.titleLarge,
                    color = NeonColors.ElectricCyan,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            
            // Motion Cards
            item {
                MotionCard(
                    title = "Permission Manager",
                    icon = Icons.Default.Security,
                    gradient = NeonGradients.BlueCyan,
                    glowColor = NeonColors.NeonBlue,
                    onClick = onNavigateToPermissions
                )
            }
            
            item {
                MotionCard(
                    title = "AI Risk Analyzer",
                    icon = Icons.Default.Analytics,
                    gradient = NeonGradients.PurpleMagenta,
                    glowColor = NeonColors.CyberPurple,
                    onClick = onNavigateToRisk
                )
            }
            
            item {
                MotionCard(
                    title = "Encrypted Logs",
                    icon = Icons.Default.Lock,
                    gradient = NeonGradients.PinkPurple,
                    glowColor = NeonColors.MagentaGlow,
                    onClick = onNavigateToLogs
                )
            }
            
            item {
                MotionCard(
                    title = "Parental Control",
                    icon = Icons.Default.ChildCare,
                    gradient = NeonGradients.RiskGradientHigh,
                    glowColor = NeonColors.SynthwavePink,
                    onClick = onNavigateToParental
                )
            }
            
            item {
                MotionCard(
                    title = "Compliance Center",
                    icon = Icons.Default.Verified,
                    gradient = NeonGradients.RiskGradientSafe,
                    glowColor = NeonColors.NeonGreen,
                    onClick = onNavigateToCompliance
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    color: Color
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .neonGlow(glowColor = color, borderRadius = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeonColors.DarkCard.copy(alpha = 0.8f)
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = color
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
    }
}
