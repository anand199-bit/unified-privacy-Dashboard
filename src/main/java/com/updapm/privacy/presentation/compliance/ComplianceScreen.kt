package com.updapm.privacy.presentation.compliance

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.updapm.privacy.ui.components.*
import com.updapm.privacy.ui.theme.NeonColors

data class ComplianceItem(
    val title: String,
    val description: String,
    val status: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplianceScreen(
    onBack: () -> Unit
) {
    val complianceItems = remember {
        listOf(
            ComplianceItem(
                title = "GDPR Compliance",
                description = "EU General Data Protection Regulation",
                status = "COMPLIANT",
                icon = Icons.Default.Verified
            ),
            ComplianceItem(
                title = "CCPA Compliance",
                description = "California Consumer Privacy Act",
                status = "COMPLIANT",
                icon = Icons.Default.Verified
            ),
            ComplianceItem(
                title = "COPPA Compliance",
                description = "Children's Online Privacy Protection",
                status = "COMPLIANT",
                icon = Icons.Default.ChildCare
            ),
            ComplianceItem(
                title = "Data Encryption",
                description = "AES-256 encryption at rest",
                status = "ACTIVE",
                icon = Icons.Default.Lock
            ),
            ComplianceItem(
                title = "Privacy Audit",
                description = "Last audit: 30 days ago",
                status = "PASSED",
                icon = Icons.Default.Assessment
            )
        )
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "COMPLIANCE CENTER",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = NeonColors.NeonGreen)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Download report */ }) {
                        Icon(Icons.Default.Download, "Download Report", tint = NeonColors.NeonGreen)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NeonColors.DarkTechBackground,
                    titleContentColor = NeonColors.NeonGreen
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
                    OverallComplianceCard()
                }
                
                item {
                    Text(
                        text = "COMPLIANCE STATUS",
                        style = MaterialTheme.typography.titleLarge,
                        color = NeonColors.NeonGreen,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                
                items(complianceItems) { item ->
                    ComplianceCard(item)
                }
                
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                
                item {
                    DownloadReportButton()
                }
            }
        }
    }
}

@Composable
private fun OverallComplianceCard() {
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
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = NeonColors.NeonGreen,
                modifier = Modifier.size(64.dp)
            )
            Column {
                Text(
                    text = "100% COMPLIANT",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = NeonColors.NeonGreen
                )
                Text(
                    text = "All privacy standards met",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Surface(
                    color = NeonColors.NeonGreen.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "✓ VERIFIED",
                        style = MaterialTheme.typography.labelMedium,
                        color = NeonColors.NeonGreen,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ComplianceCard(item: ComplianceItem) {
    val statusColor = when (item.status) {
        "COMPLIANT", "ACTIVE", "PASSED" -> NeonColors.NeonGreen
        "WARNING" -> NeonColors.SynthwavePink
        else -> NeonColors.NeonRed
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(glowColor = statusColor, borderRadius = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeonColors.DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = statusColor,
                modifier = Modifier.size(40.dp)
            )
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.6f)
                )
            }
            
            Surface(
                color = statusColor.copy(alpha = 0.3f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = item.status,
                    style = MaterialTheme.typography.labelSmall,
                    color = statusColor,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun DownloadReportButton() {
    Button(
        onClick = { /* Download compliance report */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .neonGlow(glowColor = NeonColors.ElectricCyan, borderRadius = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = NeonColors.DarkCard
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Download,
            contentDescription = null,
            tint = NeonColors.ElectricCyan
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "DOWNLOAD COMPLIANCE REPORT",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = NeonColors.ElectricCyan
        )
    }
}
