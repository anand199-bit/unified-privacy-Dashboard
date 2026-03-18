package com.updapm.privacy.data.local.dao

import androidx.room.*
import com.updapm.privacy.data.local.entity.ParentalControl
import kotlinx.coroutines.flow.Flow

@Dao
interface ParentalControlDao {
    @Query("SELECT * FROM parental_controls ORDER BY appName ASC")
    fun getAllControls(): Flow<List<ParentalControl>>
    
    @Query("SELECT * FROM parental_controls WHERE isBlocked = 1")
    fun getBlockedApps(): Flow<List<ParentalControl>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertControl(control: ParentalControl)
    
    @Update
    suspend fun updateControl(control: ParentalControl)
    
    @Delete
    suspend fun deleteControl(control: ParentalControl)
}
