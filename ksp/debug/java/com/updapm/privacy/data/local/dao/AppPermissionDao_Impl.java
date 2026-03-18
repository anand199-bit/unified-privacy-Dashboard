package com.updapm.privacy.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.updapm.privacy.data.local.entity.AppPermission;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppPermissionDao_Impl implements AppPermissionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppPermission> __insertionAdapterOfAppPermission;

  private final EntityDeletionOrUpdateAdapter<AppPermission> __deletionAdapterOfAppPermission;

  private final EntityDeletionOrUpdateAdapter<AppPermission> __updateAdapterOfAppPermission;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public AppPermissionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppPermission = new EntityInsertionAdapter<AppPermission>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `app_permissions` (`id`,`packageName`,`appName`,`permissionType`,`isGranted`,`lastAccessed`,`accessCount`,`riskLevel`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppPermission entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getPackageName());
        statement.bindString(3, entity.getAppName());
        statement.bindString(4, entity.getPermissionType());
        final int _tmp = entity.isGranted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getLastAccessed());
        statement.bindLong(7, entity.getAccessCount());
        statement.bindString(8, entity.getRiskLevel());
      }
    };
    this.__deletionAdapterOfAppPermission = new EntityDeletionOrUpdateAdapter<AppPermission>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `app_permissions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppPermission entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAppPermission = new EntityDeletionOrUpdateAdapter<AppPermission>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `app_permissions` SET `id` = ?,`packageName` = ?,`appName` = ?,`permissionType` = ?,`isGranted` = ?,`lastAccessed` = ?,`accessCount` = ?,`riskLevel` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppPermission entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getPackageName());
        statement.bindString(3, entity.getAppName());
        statement.bindString(4, entity.getPermissionType());
        final int _tmp = entity.isGranted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getLastAccessed());
        statement.bindLong(7, entity.getAccessCount());
        statement.bindString(8, entity.getRiskLevel());
        statement.bindLong(9, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM app_permissions";
        return _query;
      }
    };
  }

  @Override
  public Object insertPermission(final AppPermission permission,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppPermission.insert(permission);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPermissions(final List<AppPermission> permissions,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppPermission.insert(permissions);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePermission(final AppPermission permission,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAppPermission.handle(permission);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePermission(final AppPermission permission,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAppPermission.handle(permission);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AppPermission>> getAllPermissions() {
    final String _sql = "SELECT * FROM app_permissions ORDER BY lastAccessed DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"app_permissions"}, new Callable<List<AppPermission>>() {
      @Override
      @NonNull
      public List<AppPermission> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfPermissionType = CursorUtil.getColumnIndexOrThrow(_cursor, "permissionType");
          final int _cursorIndexOfIsGranted = CursorUtil.getColumnIndexOrThrow(_cursor, "isGranted");
          final int _cursorIndexOfLastAccessed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastAccessed");
          final int _cursorIndexOfAccessCount = CursorUtil.getColumnIndexOrThrow(_cursor, "accessCount");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final List<AppPermission> _result = new ArrayList<AppPermission>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppPermission _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpPermissionType;
            _tmpPermissionType = _cursor.getString(_cursorIndexOfPermissionType);
            final boolean _tmpIsGranted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsGranted);
            _tmpIsGranted = _tmp != 0;
            final long _tmpLastAccessed;
            _tmpLastAccessed = _cursor.getLong(_cursorIndexOfLastAccessed);
            final int _tmpAccessCount;
            _tmpAccessCount = _cursor.getInt(_cursorIndexOfAccessCount);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            _item = new AppPermission(_tmpId,_tmpPackageName,_tmpAppName,_tmpPermissionType,_tmpIsGranted,_tmpLastAccessed,_tmpAccessCount,_tmpRiskLevel);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<AppPermission>> getPermissionsByPackage(final String packageName) {
    final String _sql = "SELECT * FROM app_permissions WHERE packageName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packageName);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"app_permissions"}, new Callable<List<AppPermission>>() {
      @Override
      @NonNull
      public List<AppPermission> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfPermissionType = CursorUtil.getColumnIndexOrThrow(_cursor, "permissionType");
          final int _cursorIndexOfIsGranted = CursorUtil.getColumnIndexOrThrow(_cursor, "isGranted");
          final int _cursorIndexOfLastAccessed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastAccessed");
          final int _cursorIndexOfAccessCount = CursorUtil.getColumnIndexOrThrow(_cursor, "accessCount");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final List<AppPermission> _result = new ArrayList<AppPermission>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppPermission _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpPermissionType;
            _tmpPermissionType = _cursor.getString(_cursorIndexOfPermissionType);
            final boolean _tmpIsGranted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsGranted);
            _tmpIsGranted = _tmp != 0;
            final long _tmpLastAccessed;
            _tmpLastAccessed = _cursor.getLong(_cursorIndexOfLastAccessed);
            final int _tmpAccessCount;
            _tmpAccessCount = _cursor.getInt(_cursorIndexOfAccessCount);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            _item = new AppPermission(_tmpId,_tmpPackageName,_tmpAppName,_tmpPermissionType,_tmpIsGranted,_tmpLastAccessed,_tmpAccessCount,_tmpRiskLevel);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<AppPermission>> getPermissionsByRisk(final String riskLevel) {
    final String _sql = "SELECT * FROM app_permissions WHERE riskLevel = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, riskLevel);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"app_permissions"}, new Callable<List<AppPermission>>() {
      @Override
      @NonNull
      public List<AppPermission> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfPermissionType = CursorUtil.getColumnIndexOrThrow(_cursor, "permissionType");
          final int _cursorIndexOfIsGranted = CursorUtil.getColumnIndexOrThrow(_cursor, "isGranted");
          final int _cursorIndexOfLastAccessed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastAccessed");
          final int _cursorIndexOfAccessCount = CursorUtil.getColumnIndexOrThrow(_cursor, "accessCount");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final List<AppPermission> _result = new ArrayList<AppPermission>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppPermission _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpPermissionType;
            _tmpPermissionType = _cursor.getString(_cursorIndexOfPermissionType);
            final boolean _tmpIsGranted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsGranted);
            _tmpIsGranted = _tmp != 0;
            final long _tmpLastAccessed;
            _tmpLastAccessed = _cursor.getLong(_cursorIndexOfLastAccessed);
            final int _tmpAccessCount;
            _tmpAccessCount = _cursor.getInt(_cursorIndexOfAccessCount);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            _item = new AppPermission(_tmpId,_tmpPackageName,_tmpAppName,_tmpPermissionType,_tmpIsGranted,_tmpLastAccessed,_tmpAccessCount,_tmpRiskLevel);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
