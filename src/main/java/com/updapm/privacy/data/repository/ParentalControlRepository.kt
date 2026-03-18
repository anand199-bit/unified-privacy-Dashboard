package com.updapm.privacy.data.repository

import com.updapm.privacy.data.local.dao.ParentalControlDao
import com.updapm.privacy.data.local.entity.ParentalControl
import com.updapm.privacy.data.service.AppPermissionService
import com.updapm.privacy.data.service.UsageStatsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParentalControlRepository @Inject constructor(
    private val parentalControlDao: ParentalControlDao,
    private val appPermissionService: AppPermissionService,
    private val usageStatsService: UsageStatsService
) {
    fun getAllControls(): Flow<List<ParentalControl>> = 
        parentalControlDao.getAllControls()
    
    fun getBlockedApps(): Flow<List<ParentalControl>> =
        parentalControlDao.getBlockedApps()
    
    suspend fun insertControl(control: ParentalControl) =
        parentalControlDao.insertControl(control)
    
    suspend fun updateControl(control: ParentalControl) =
        parentalControlDao.updateControl(control)
    
    suspend fun deleteControl(control: ParentalControl) =
        parentalControlDao.deleteControl(control)
    
    suspend fun syncInstalledApps() {
        try {
            val installedApps = appPermissionService.fetchAllInstalledApps()
            
            val controls = installedApps.map { app ->
                val usageMinutes = try {
                    usageStatsService.getAppAccessCount(app.packageName, 1)
                } catch (e: Exception) {
                    0
                }
                
                ParentalControl(
                    packageName = app.packageName,
                    appName = app.appName,
                    isBlocked = false,
                    timeLimit = 60, // Default 60 minutes
                    usedTime = usageMinutes
                )
            }
            
            // Insert only new apps (Room will handle conflicts)
            controls.forEach { control ->
                try {
                    insertControl(control)
                } catch (e: Exception) {
                    // App already exists, skip
                }
            }
        } catch (e: Exception) {
            // If sync fails, keep existing data
        }
    }
}
