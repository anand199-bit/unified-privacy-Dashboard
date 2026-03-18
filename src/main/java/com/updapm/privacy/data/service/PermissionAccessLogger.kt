package com.updapm.privacy.data.service

import android.content.Context
import com.updapm.privacy.data.repository.LogRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionAccessLogger @Inject constructor(
    @ApplicationContext private val context: Context,
    private val logRepository: LogRepository,
    private val usageStatsService: UsageStatsService
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    
    fun logPermissionAccess(
        packageName: String,
        appName: String,
        permissionType: String,
        isGranted: Boolean
    ) {
        scope.launch {
            val severity = if (isGranted) "INFO" else "WARNING"
            val action = if (isGranted) "GRANTED" else "DENIED"
            
            logRepository.logEvent(
                eventType = "PERMISSION_ACCESS",
                data = "$appName requested $permissionType - $action",
                appPackage = packageName,
                severity = severity
            )
        }
    }
    
    fun logAppLaunch(packageName: String, appName: String) {
        scope.launch {
            logRepository.logEvent(
                eventType = "APP_LAUNCH",
                data = "$appName was launched",
                appPackage = packageName,
                severity = "INFO"
            )
        }
    }
    
    fun logHighRiskActivity(packageName: String, appName: String, reason: String) {
        scope.launch {
            logRepository.logEvent(
                eventType = "HIGH_RISK_ACTIVITY",
                data = "$appName: $reason",
                appPackage = packageName,
                severity = "CRITICAL"
            )
        }
    }
    
    suspend fun scanAndLogRecentActivity() {
        val endTime = System.currentTimeMillis()
        val startTime = endTime - (24 * 60 * 60 * 1000L) // Last 24 hours
        
        val usageStats = usageStatsService.getAppUsageStats(startTime, endTime)
        
        usageStats.forEach { (packageName, stats) ->
            if (stats.totalTimeInForeground > 0) {
                try {
                    val appInfo = context.packageManager.getApplicationInfo(packageName, 0)
                    val appName = context.packageManager.getApplicationLabel(appInfo).toString()
                    
                    logRepository.logEvent(
                        eventType = "APP_USAGE",
                        data = "$appName used for ${stats.totalTimeInForeground / 60000} minutes",
                        appPackage = packageName,
                        severity = "INFO"
                    )
                } catch (e: Exception) {
                    // App not found or error
                }
            }
        }
    }
}
