package com.updapm.privacy.data.repository

import com.updapm.privacy.data.local.dao.AppPermissionDao
import com.updapm.privacy.data.local.entity.AppPermission
import com.updapm.privacy.data.service.AppPermissionService
import com.updapm.privacy.data.service.UsageStatsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionRepository @Inject constructor(
    private val appPermissionDao: AppPermissionDao,
    private val appPermissionService: AppPermissionService,
    private val usageStatsService: UsageStatsService
) {
    fun getAllPermissions(): Flow<List<AppPermission>> = 
        appPermissionDao.getAllPermissions()
    
    fun getPermissionsByPackage(packageName: String): Flow<List<AppPermission>> =
        appPermissionDao.getPermissionsByPackage(packageName)
    
    fun getPermissionsByRisk(riskLevel: String): Flow<List<AppPermission>> =
        appPermissionDao.getPermissionsByRisk(riskLevel)
    
    suspend fun insertPermission(permission: AppPermission) =
        appPermissionDao.insertPermission(permission)
    
    suspend fun insertPermissions(permissions: List<AppPermission>) =
        appPermissionDao.insertPermissions(permissions)
    
    suspend fun updatePermission(permission: AppPermission) =
        appPermissionDao.updatePermission(permission)
    
    suspend fun deletePermission(permission: AppPermission) =
        appPermissionDao.deletePermission(permission)
    
    suspend fun syncRealPermissions() {
        try {
            val realPermissions = appPermissionService.fetchAllPermissions()
            
            // Enhance with usage stats if available
            val enhancedPermissions = realPermissions.map { permission ->
                try {
                    val accessCount = usageStatsService.getAppAccessCount(permission.packageName)
                    permission.copy(accessCount = accessCount)
                } catch (e: Exception) {
                    permission
                }
            }
            
            // Clear old data and insert fresh data
            if (enhancedPermissions.isNotEmpty()) {
                appPermissionDao.deleteAll()
                insertPermissions(enhancedPermissions)
            }
        } catch (e: Exception) {
            // If sync fails, keep existing data
        }
    }
    
    suspend fun refreshAppPermissions(packageName: String) {
        val permissions = appPermissionService.fetchAppPermissions(packageName)
        val accessCount = usageStatsService.getAppAccessCount(packageName)
        
        val enhancedPermissions = permissions.map { 
            it.copy(accessCount = accessCount) 
        }
        
        insertPermissions(enhancedPermissions)
    }
}
