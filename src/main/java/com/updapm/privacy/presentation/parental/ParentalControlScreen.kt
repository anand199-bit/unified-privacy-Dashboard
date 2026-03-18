package com.updapm.privacy.presentation.parental

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.updapm.privacy.data.local.entity.ParentalControl
import com.updapm.privacy.ui.components.*
import com.updapm.privacy.ui.theme.NeonColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentalControlScreen(
    onBack: () -> Unit,
    viewModel: ParentalControlViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "PARENTAL CONTROL",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = NeonColors.SynthwavePink)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NeonColors.DarkTechBackground,
                    titleContentColor = NeonColors.SynthwavePink
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    StatsCard(blockedCount = uiState.blockedCount)
                }
                
                item {
                    Text(
                        text = "APP CONTROLS",
                        style = MaterialTheme.typography.titleLarge,
                        color = NeonColors.SynthwavePink,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                
                if (uiState.controls.isEmpty()) {
                    item {
                        EmptyControlsCard()
                    }
                } else {
                    items(uiState.controls) { control ->
                        ParentalControlCard(
                            control = control,
                            onToggleBlock = { viewModel.toggleBlock(control) },
                            onUpdateTimeLimit = { minutes ->
                                viewModel.updateTimeLimit(control, minutes)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatsCard(blockedCount: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(glowColor = NeonColors.NeonRed, borderRadius = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeonColors.DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Block,
                contentDescription = null,
                tint = NeonColors.NeonRed,
                modifier = Modifier.size(48.dp)
            )
            Column {
                Text(
                    text = "$blockedCount APPS BLOCKED",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = NeonColors.NeonRed
                )
                Text(
                    text = "Active parental controls",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun ParentalControlCard(
    control: ParentalControl,
    onToggleBlock: () -> Unit,
    onUpdateTimeLimit: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(
                glowColor = if (control.isBlocked) NeonColors.NeonRed else NeonColors.NeonGreen,
                borderRadius = 12.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = NeonColors.DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = control.appName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                    Surface(
                        color = if (control.isBlocked) 
                            NeonColors.NeonRed.copy(alpha = 0.3f) 
                        else 
                            NeonColors.NeonGreen.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(
                            text = if (control.isBlocked) "BLOCKED" else "ALLOWED",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (control.isBlocked) NeonColors.NeonRed else NeonColors.NeonGreen,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NeonSwitch(
                        checked = control.isBlocked,
                        onCheckedChange = { onToggleBlock() }
                    )
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = "Expand",
                            tint = NeonColors.SynthwavePink
                        )
                    }
                }
            }
            
            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Time Limit: ${control.timeLimit} min/day",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "Used: ${control.usedTime} min",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "Allowed: ${control.allowedStartTime} - ${control.allowedEndTime}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyControlsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(glowColor = NeonColors.GridGray, borderRadius = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeonColors.DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ChildCare,
                contentDescription = null,
                tint = NeonColors.GridGray,
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "No parental controls set",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
    }
}
