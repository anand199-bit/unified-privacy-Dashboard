package com.updapm.privacy.presentation.permissions

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
import com.updapm.privacy.data.local.entity.AppPermission
import com.updapm.privacy.ui.components.*
import com.updapm.privacy.ui.theme.NeonColors
import com.updapm.privacy.ui.theme.NeonGradients
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionManagerScreen(
    onBack: () -> Unit,
    viewModel: PermissionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "PERMISSION MANAGER",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = NeonColors.ElectricCyan)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NeonColors.DarkTechBackground,
                    titleContentColor = NeonColors.ElectricCyan
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
                uiState.groupedByApp.forEach { (appName, permissions) ->
                    item(key = appName) {
                        AppPermissionCard(
                            appName = appName,
                            permissions = permissions,
                            onTogglePermission = { viewModel.togglePermission(it) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AppPermissionCard(
    appName: String,
    permissions: List<AppPermission>,
    onTogglePermission: (AppPermission) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(glowColor = NeonColors.NeonBlue, borderRadius = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeonColors.DarkCard.copy(alpha = 0.9f)
        ),
        shape = RoundedCornerShape(16.dp)
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
                        text = appName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = NeonColors.ElectricCyan
                    )
                    Text(
                        text = "${permissions.size} permissions",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
                
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand",
                        tint = NeonColors.NeonBlue
                    )
                }
            }
            
            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier.padding(top = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    permissions.forEach { permission ->
                        PermissionItem(
                            permission = permission,
                            onToggle = { onTogglePermission(permission) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PermissionItem(
    permission: AppPermission,
    onToggle: () -> Unit
) {
    val riskColor = when (permission.riskLevel) {
        "HIGH" -> NeonColors.NeonRed
        "MEDIUM" -> NeonColors.SynthwavePink
        else -> NeonColors.NeonGreen
    }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = NeonColors.GridGray.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = permission.permissionType.substringAfterLast("."),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = riskColor.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = permission.riskLevel,
                        style = MaterialTheme.typography.labelSmall,
                        color = riskColor,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Text(
                    text = "Used ${permission.accessCount}x",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.5f)
                )
            }
        }
        
        NeonSwitch(
            checked = permission.isGranted,
            onCheckedChange = { onToggle() }
        )
    }
}
