package com.updapm.privacy.di

import android.content.Context
import androidx.room.Room
import com.updapm.privacy.data.local.AppDatabase
import com.updapm.privacy.data.local.dao.AppPermissionDao
import com.updapm.privacy.data.local.dao.EncryptedLogDao
import com.updapm.privacy.data.local.dao.ParentalControlDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "updapm_database"
        ).build()
    }
    
    @Provides
    fun provideAppPermissionDao(database: AppDatabase): AppPermissionDao {
        return database.appPermissionDao()
    }
    
    @Provides
    fun provideEncryptedLogDao(database: AppDatabase): EncryptedLogDao {
        return database.encryptedLogDao()
    }
    
    @Provides
    fun provideParentalControlDao(database: AppDatabase): ParentalControlDao {
        return database.parentalControlDao()
    }
}
