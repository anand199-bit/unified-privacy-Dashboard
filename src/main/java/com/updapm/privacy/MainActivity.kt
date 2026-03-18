package com.updapm.privacy

import android.app.AppOpsManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.updapm.privacy.data.service.PermissionMonitorService
import com.updapm.privacy.navigation.NavGraph
import com.updapm.privacy.ui.theme.NeonTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    @Inject
    lateinit var permissionMonitorService: PermissionMonitorService
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        // Check and request usage stats permission
        checkUsageStatsPermission()
        
        // Start background monitoring
        permissionMonitorService.startMonitoring()
        
        setContent {
            NeonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
    
    private fun checkUsageStatsPermission() {
        try {
            val appOps = getSystemService(APP_OPS_SERVICE) as? AppOpsManager ?: return
            val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                appOps.unsafeCheckOpNoThrow(
                    AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(),
                    packageName
                )
            } else {
                @Suppress("DEPRECATION")
                appOps.checkOpNoThrow(
                    AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(),
                    packageName
                )
            }
            
            // Don't force navigation, just check silently
            // User can grant permission later if needed
        } catch (e: Exception) {
            // Ignore permission check errors
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        permissionMonitorService.stopMonitoring()
    }
}
