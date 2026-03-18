package com.updapm.privacy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_permissions")
data class AppPermission(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val packageName: String,
    val appName: String,
    val permissionType: String,
    val isGranted: Boolean,
    val lastAccessed: Long,
    val accessCount: Int = 0,
    val riskLevel: String = "LOW" // LOW, MEDIUM, HIGH
)
