package com.updapm.privacy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "encrypted_logs")
data class EncryptedLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long,
    val eventType: String,
    val encryptedData: String,
    val appPackage: String,
    val severity: String // INFO, WARNING, CRITICAL
)
