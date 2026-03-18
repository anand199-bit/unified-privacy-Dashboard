package com.updapm.privacy.data.repository

import com.updapm.privacy.data.local.dao.EncryptedLogDao
import com.updapm.privacy.data.local.entity.EncryptedLog
import com.updapm.privacy.util.AESEncryptionHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogRepository @Inject constructor(
    private val encryptedLogDao: EncryptedLogDao
) {
    fun getRecentLogs(): Flow<List<EncryptedLog>> = 
        encryptedLogDao.getRecentLogs()
    
    fun getLogsBySeverity(severity: String): Flow<List<EncryptedLog>> =
        encryptedLogDao.getLogsBySeverity(severity)
    
    suspend fun logEvent(eventType: String, data: String, appPackage: String, severity: String) {
        val encryptedData = AESEncryptionHelper.encrypt(data)
        val log = EncryptedLog(
            timestamp = System.currentTimeMillis(),
            eventType = eventType,
            encryptedData = encryptedData,
            appPackage = appPackage,
            severity = severity
        )
        encryptedLogDao.insertLog(log)
    }
    
    suspend fun deleteOldLogs(daysOld: Int = 30) {
        val timestamp = System.currentTimeMillis() - (daysOld * 24 * 60 * 60 * 1000L)
        encryptedLogDao.deleteOldLogs(timestamp)
    }
    
    suspend fun clearAllLogs() {
        encryptedLogDao.deleteAll()
    }
}
