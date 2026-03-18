package com.updapm.privacy.data.service

import android.app.AppOpsManager
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import android.os.Process
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsageStatsService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as? UsageStatsManager
    
    fun hasUsageStatsPermission(): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOps.unsafeCheckOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName
            )
        } else {
            @Suppress("DEPRECATION")
            appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName
            )
        }
        return mode == AppOpsManager.MODE_ALLOWED
    }
    
    suspend fun getAppUsageStats(startTime: Long, endTime: Long): Map<String, UsageStats> = 
        withContext(Dispatchers.IO) {
            try {
                if (!hasUsageStatsPermission() || usageStatsManager == null) {
                    return@withContext emptyMap()
                }
                
                usageStatsManager.queryAndAggregateUsageStats(startTime, endTime) ?: emptyMap()
            } catch (e: Exception) {
                emptyMap()
            }
        }
    
    suspend fun getAppAccessCount(packageName: String, daysBack: Int = 7): Int = 
        withContext(Dispatchers.IO) {
            try {
                val endTime = System.currentTimeMillis()
                val startTime = endTime - (daysBack * 24 * 60 * 60 * 1000L)
                
                val stats = getAppUsageStats(startTime, endTime)
                stats[packageName]?.let { 
                    // Estimate access count from total time in foreground
                    (it.totalTimeInForeground / 60000).toInt() // Convert to minutes
                } ?: 0
            } catch (e: Exception) {
                0
            }
        }
}
