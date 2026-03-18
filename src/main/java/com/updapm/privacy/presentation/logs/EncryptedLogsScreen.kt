package com.updapm.privacy.presentation.logs

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
import com.updapm.privacy.data.local.entity.EncryptedLog
import com.updapm.privacy.ui.components.*
import com.updapm.privacy.ui.theme.NeonColors
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncryptedLogsScreen(
    onBack: () -> Unit,
    viewModel: LogsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "ENCRYPTED LOGS",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = NeonColors.MagentaGlow)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.clearLogs() }) {
                        Icon(Icons.Default.Delete, "Clear Logs", tint = NeonColors.NeonRed)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NeonColors.DarkTechBackground,
                    titleContentColor = NeonColors.MagentaGlow
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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    EncryptionStatusCard()
                }
                
                item {
                    Text(
                        text = "RECENT ACTIVITY",
                        style = MaterialTheme.typography.titleLarge,
                        color = NeonColors.MagentaGlow,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                
                if (uiState.logs.isEmpty()) {
                    item {
                        EmptyLogsCard()
                    }
                } else {
                    items(uiState.logs) { log ->
                        LogCard(log)
                    }
                }
            }
        }
    }
}

@Composable
private fun EncryptionStatusCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(glowColor = NeonColors.NeonGreen, borderRadius = 16.dp),
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
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = NeonColors.NeonGreen,
                modifier = Modifier.size(48.dp)
            )
            Column {
                Text(
                    text = "AES-256 ENCRYPTION",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = NeonColors.NeonGreen
                )
                Text(
                    text = "All logs are encrypted at rest",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Surface(
                    color = NeonColors.NeonGreen.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = "✓ ACTIVE",
                        style = MaterialTheme.typography.labelSmall,
                        color = NeonColors.NeonGreen,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun LogCard(log: EncryptedLog) {
    val severityColor = when (log.severity) {
        "CRITICAL" -> NeonColors.NeonRed
        "WARNING" -> NeonColors.SynthwavePink
        else -> NeonColors.ElectricCyan
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(glowColor = severityColor, borderRadius = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeonColors.DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = severityColor.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = log.severity,
                        style = MaterialTheme.typography.labelSmall,
                        color = severityColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                
                Text(
                    text = formatTimestamp(log.timestamp),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.5f)
                )
            }
            
            Text(
                text = log.eventType,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            
            Text(
                text = log.appPackage,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.6f)
            )
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = NeonColors.NeonGreen,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Encrypted Data: ${log.encryptedData.take(20)}...",
                    style = MaterialTheme.typography.bodySmall,
                    color = NeonColors.NeonGreen.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
private fun EmptyLogsCard() {
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
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = NeonColors.GridGray,
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "No logs available",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
