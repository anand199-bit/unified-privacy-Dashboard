package com.updapm.privacy.data.service

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import com.updapm.privacy.data.local.entity.AppPermission
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPermissionService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val packageManager = context.packageManager
    
    suspend fun fetchAllInstalledApps(): List<AppInfo> = withContext(Dispatchers.IO) {
        try {
            val apps = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getInstalledApplications(PackageManager.ApplicationInfoFlags.of(0))
            } else {
                @Suppress("DEPRECATION")
                packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            }
            
            apps.filter { it.flags and ApplicationInfo.FLAG_SYSTEM == 0 }
                .mapNotNull { appInfo ->
                    try {
                        AppInfo(
                            packageName = appInfo.packageName,
                            appName = appInfo.loadLabel(packageManager).toString(),
                            icon = appInfo.loadIcon(packageManager)
                        )
                    } catch (e: Exception) {
                        null
                    }
                }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun fetchAppPermissions(packageName: String): List<AppPermission> = withContext(Dispatchers.IO) {
        try {
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getPackageInfo(
                    packageName,
                    PackageManager.PackageInfoFlags.of(PackageManager.GET_PERMISSIONS.toLong())
                )
            } else {
                @Suppress("DEPRECATION")
                packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
            }
            
            val appName = packageManager.getApplicationLabel(
                packageManager.getApplicationInfo(packageName, 0)
            ).toString()
            
            packageInfo.requestedPermissions?.mapIndexed { index, permission ->
                val isGranted = (packageInfo.requestedPermissionsFlags[index] and 
                    PackageManager.PERMISSION_GRANTED) != 0
                
                AppPermission(
                    packageName = packageName,
                    appName = appName,
                    permissionType = permission,
                    isGranted = isGranted,
                    lastAccessed = System.currentTimeMillis(),
                    accessCount = 0,
                    riskLevel = determineRiskLevel(permission)
                )
            } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun fetchAllPermissions(): List<AppPermission> = withContext(Dispatchers.IO) {
        try {
            val allPermissions = mutableListOf<AppPermission>()
            val apps = fetchAllInstalledApps()
            
            apps.forEach { app ->
                try {
                    allPermissions.addAll(fetchAppPermissions(app.packageName))
                } catch (e: Exception) {
                    // Skip apps that fail
                }
            }
            
            allPermissions
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    private fun determineRiskLevel(permission: String): String {
        return when {
            permission in HIGH_RISK_PERMISSIONS -> "HIGH"
            permission in MEDIUM_RISK_PERMISSIONS -> "MEDIUM"
            else -> "LOW"
        }
    }
    
    companion object {
        private val HIGH_RISK_PERMISSIONS = setOf(
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.SEND_SMS",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.CAMERA",
            "android.permission.RECORD_AUDIO",
            "android.permission.READ_CALL_LOG",
            "android.permission.WRITE_CALL_LOG",
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR"
        )
        
        private val MEDIUM_RISK_PERMISSIONS = setOf(
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.GET_ACCOUNTS",
            "android.permission.READ_PHONE_STATE",
            "android.permission.ACCESS_BACKGROUND_LOCATION"
        )
    }
}

data class AppInfo(
    val packageName: String,
    val appName: String,
    val icon: android.graphics.drawable.Drawable
)
