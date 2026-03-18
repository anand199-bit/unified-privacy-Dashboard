package com.updapm.privacy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parental_controls")
data class ParentalControl(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val packageName: String,
    val appName: String,
    val isBlocked: Boolean,
    val timeLimit: Int, // minutes per day
    val usedTime: Int = 0,
    val allowedStartTime: String = "00:00",
    val allowedEndTime: String = "23:59"
)
