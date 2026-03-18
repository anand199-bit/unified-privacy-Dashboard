package com.updapm.privacy.data.local.dao

import androidx.room.*
import com.updapm.privacy.data.local.entity.EncryptedLog
import kotlinx.coroutines.flow.Flow

@Dao
interface EncryptedLogDao {
    @Query("SELECT * FROM encrypted_logs ORDER BY timestamp DESC LIMIT 100")
    fun getRecentLogs(): Flow<List<EncryptedLog>>
    
    @Query("SELECT * FROM encrypted_logs WHERE severity = :severity ORDER BY timestamp DESC")
    fun getLogsBySeverity(severity: String): Flow<List<EncryptedLog>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: EncryptedLog)
    
    @Query("DELETE FROM encrypted_logs WHERE timestamp < :timestamp")
    suspend fun deleteOldLogs(timestamp: Long)
    
    @Query("DELETE FROM encrypted_logs")
    suspend fun deleteAll()
}
