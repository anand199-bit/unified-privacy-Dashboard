package com.updapm.privacy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.updapm.privacy.data.local.dao.AppPermissionDao
import com.updapm.privacy.data.local.dao.EncryptedLogDao
import com.updapm.privacy.data.local.dao.ParentalControlDao
import com.updapm.privacy.data.local.entity.AppPermission
import com.updapm.privacy.data.local.entity.EncryptedLog
import com.updapm.privacy.data.local.entity.ParentalControl

@Database(
    entities = [
        AppPermission::class,
        EncryptedLog::class,
        ParentalControl::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appPermissionDao(): AppPermissionDao
    abstract fun encryptedLogDao(): EncryptedLogDao
    abstract fun parentalControlDao(): ParentalControlDao
}
