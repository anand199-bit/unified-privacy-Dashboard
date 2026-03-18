package com.updapm.privacy.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.updapm.privacy.data.local.dao.AppPermissionDao;
import com.updapm.privacy.data.local.dao.AppPermissionDao_Impl;
import com.updapm.privacy.data.local.dao.EncryptedLogDao;
import com.updapm.privacy.data.local.dao.EncryptedLogDao_Impl;
import com.updapm.privacy.data.local.dao.ParentalControlDao;
import com.updapm.privacy.data.local.dao.ParentalControlDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile AppPermissionDao _appPermissionDao;

  private volatile EncryptedLogDao _encryptedLogDao;

  private volatile ParentalControlDao _parentalControlDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_permissions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `packageName` TEXT NOT NULL, `appName` TEXT NOT NULL, `permissionType` TEXT NOT NULL, `isGranted` INTEGER NOT NULL, `lastAccessed` INTEGER NOT NULL, `accessCount` INTEGER NOT NULL, `riskLevel` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `encrypted_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `timestamp` INTEGER NOT NULL, `eventType` TEXT NOT NULL, `encryptedData` TEXT NOT NULL, `appPackage` TEXT NOT NULL, `severity` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `parental_controls` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `packageName` TEXT NOT NULL, `appName` TEXT NOT NULL, `isBlocked` INTEGER NOT NULL, `timeLimit` INTEGER NOT NULL, `usedTime` INTEGER NOT NULL, `allowedStartTime` TEXT NOT NULL, `allowedEndTime` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '11b1ea587f92b1e865ff2efe239aa26b')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `app_permissions`");
        db.execSQL("DROP TABLE IF EXISTS `encrypted_logs`");
        db.execSQL("DROP TABLE IF EXISTS `parental_controls`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsAppPermissions = new HashMap<String, TableInfo.Column>(8);
        _columnsAppPermissions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("appName", new TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("permissionType", new TableInfo.Column("permissionType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("isGranted", new TableInfo.Column("isGranted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("lastAccessed", new TableInfo.Column("lastAccessed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("accessCount", new TableInfo.Column("accessCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppPermissions.put("riskLevel", new TableInfo.Column("riskLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppPermissions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppPermissions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppPermissions = new TableInfo("app_permissions", _columnsAppPermissions, _foreignKeysAppPermissions, _indicesAppPermissions);
        final TableInfo _existingAppPermissions = TableInfo.read(db, "app_permissions");
        if (!_infoAppPermissions.equals(_existingAppPermissions)) {
          return new RoomOpenHelper.ValidationResult(false, "app_permissions(com.updapm.privacy.data.local.entity.AppPermission).\n"
                  + " Expected:\n" + _infoAppPermissions + "\n"
                  + " Found:\n" + _existingAppPermissions);
        }
        final HashMap<String, TableInfo.Column> _columnsEncryptedLogs = new HashMap<String, TableInfo.Column>(6);
        _columnsEncryptedLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEncryptedLogs.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEncryptedLogs.put("eventType", new TableInfo.Column("eventType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEncryptedLogs.put("encryptedData", new TableInfo.Column("encryptedData", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEncryptedLogs.put("appPackage", new TableInfo.Column("appPackage", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEncryptedLogs.put("severity", new TableInfo.Column("severity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEncryptedLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEncryptedLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEncryptedLogs = new TableInfo("encrypted_logs", _columnsEncryptedLogs, _foreignKeysEncryptedLogs, _indicesEncryptedLogs);
        final TableInfo _existingEncryptedLogs = TableInfo.read(db, "encrypted_logs");
        if (!_infoEncryptedLogs.equals(_existingEncryptedLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "encrypted_logs(com.updapm.privacy.data.local.entity.EncryptedLog).\n"
                  + " Expected:\n" + _infoEncryptedLogs + "\n"
                  + " Found:\n" + _existingEncryptedLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsParentalControls = new HashMap<String, TableInfo.Column>(8);
        _columnsParentalControls.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParentalControls.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParentalControls.put("appName", new TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParentalControls.put("isBlocked", new TableInfo.Column("isBlocked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParentalControls.put("timeLimit", new TableInfo.Column("timeLimit", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParentalControls.put("usedTime", new TableInfo.Column("usedTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParentalControls.put("allowedStartTime", new TableInfo.Column("allowedStartTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsParentalControls.put("allowedEndTime", new TableInfo.Column("allowedEndTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysParentalControls = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesParentalControls = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoParentalControls = new TableInfo("parental_controls", _columnsParentalControls, _foreignKeysParentalControls, _indicesParentalControls);
        final TableInfo _existingParentalControls = TableInfo.read(db, "parental_controls");
        if (!_infoParentalControls.equals(_existingParentalControls)) {
          return new RoomOpenHelper.ValidationResult(false, "parental_controls(com.updapm.privacy.data.local.entity.ParentalControl).\n"
                  + " Expected:\n" + _infoParentalControls + "\n"
                  + " Found:\n" + _existingParentalControls);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "11b1ea587f92b1e865ff2efe239aa26b", "6f63070100a5677e53dd7ee1f8c60894");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "app_permissions","encrypted_logs","parental_controls");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `app_permissions`");
      _db.execSQL("DELETE FROM `encrypted_logs`");
      _db.execSQL("DELETE FROM `parental_controls`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AppPermissionDao.class, AppPermissionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EncryptedLogDao.class, EncryptedLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ParentalControlDao.class, ParentalControlDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AppPermissionDao appPermissionDao() {
    if (_appPermissionDao != null) {
      return _appPermissionDao;
    } else {
      synchronized(this) {
        if(_appPermissionDao == null) {
          _appPermissionDao = new AppPermissionDao_Impl(this);
        }
        return _appPermissionDao;
      }
    }
  }

  @Override
  public EncryptedLogDao encryptedLogDao() {
    if (_encryptedLogDao != null) {
      return _encryptedLogDao;
    } else {
      synchronized(this) {
        if(_encryptedLogDao == null) {
          _encryptedLogDao = new EncryptedLogDao_Impl(this);
        }
        return _encryptedLogDao;
      }
    }
  }

  @Override
  public ParentalControlDao parentalControlDao() {
    if (_parentalControlDao != null) {
      return _parentalControlDao;
    } else {
      synchronized(this) {
        if(_parentalControlDao == null) {
          _parentalControlDao = new ParentalControlDao_Impl(this);
        }
        return _parentalControlDao;
      }
    }
  }
}
