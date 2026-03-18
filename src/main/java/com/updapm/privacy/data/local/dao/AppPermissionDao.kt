package com.updapm.privacy.data.local.dao

import androidx.room.*
import com.updapm.privacy.data.local.entity.AppPermission
import kotlinx.coroutines.flow.Flow

@Dao
interface AppPermissionDao {
    @Query("SELECT * FROM app_permissions ORDER BY lastAccessed DESC")
    fun getAllPermissions(): Flow<List<AppPermission>>
    
    @Query("SELECT * FROM app_permissions WHERE packageName = :packageName")
    fun getPermissionsByPackage(packageName: String): Flow<List<AppPermission>>
    
    @Query("SELECT * FROM app_permissions WHERE riskLevel = :riskLevel")
    fun getPermissionsByRisk(riskLevel: String): Flow<List<AppPermission>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPermission(permission: AppPermission)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPermissions(permissions: List<AppPermission>)
    
    @Update
    suspend fun updatePermission(permission: AppPermission)
    
    @Delete
    suspend fun deletePermission(permission: AppPermission)
    
    @Query("DELETE FROM app_permissions")
    suspend fun deleteAll()
}
