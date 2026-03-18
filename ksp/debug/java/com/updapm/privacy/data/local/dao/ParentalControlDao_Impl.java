package com.updapm.privacy.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.updapm.privacy.data.local.entity.ParentalControl;
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
public final class ParentalControlDao_Impl implements ParentalControlDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ParentalControl> __insertionAdapterOfParentalControl;

  private final EntityDeletionOrUpdateAdapter<ParentalControl> __deletionAdapterOfParentalControl;

  private final EntityDeletionOrUpdateAdapter<ParentalControl> __updateAdapterOfParentalControl;

  public ParentalControlDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfParentalControl = new EntityInsertionAdapter<ParentalControl>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `parental_controls` (`id`,`packageName`,`appName`,`isBlocked`,`timeLimit`,`usedTime`,`allowedStartTime`,`allowedEndTime`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ParentalControl entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getPackageName());
        statement.bindString(3, entity.getAppName());
        final int _tmp = entity.isBlocked() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.getTimeLimit());
        statement.bindLong(6, entity.getUsedTime());
        statement.bindString(7, entity.getAllowedStartTime());
        statement.bindString(8, entity.getAllowedEndTime());
      }
    };
    this.__deletionAdapterOfParentalControl = new EntityDeletionOrUpdateAdapter<ParentalControl>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `parental_controls` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ParentalControl entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfParentalControl = new EntityDeletionOrUpdateAdapter<ParentalControl>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `parental_controls` SET `id` = ?,`packageName` = ?,`appName` = ?,`isBlocked` = ?,`timeLimit` = ?,`usedTime` = ?,`allowedStartTime` = ?,`allowedEndTime` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ParentalControl entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getPackageName());
        statement.bindString(3, entity.getAppName());
        final int _tmp = entity.isBlocked() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.getTimeLimit());
        statement.bindLong(6, entity.getUsedTime());
        statement.bindString(7, entity.getAllowedStartTime());
        statement.bindString(8, entity.getAllowedEndTime());
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insertControl(final ParentalControl control,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfParentalControl.insert(control);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteControl(final ParentalControl control,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfParentalControl.handle(control);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateControl(final ParentalControl control,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfParentalControl.handle(control);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ParentalControl>> getAllControls() {
    final String _sql = "SELECT * FROM parental_controls ORDER BY appName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"parental_controls"}, new Callable<List<ParentalControl>>() {
      @Override
      @NonNull
      public List<ParentalControl> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfIsBlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBlocked");
          final int _cursorIndexOfTimeLimit = CursorUtil.getColumnIndexOrThrow(_cursor, "timeLimit");
          final int _cursorIndexOfUsedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "usedTime");
          final int _cursorIndexOfAllowedStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "allowedStartTime");
          final int _cursorIndexOfAllowedEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "allowedEndTime");
          final List<ParentalControl> _result = new ArrayList<ParentalControl>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ParentalControl _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final boolean _tmpIsBlocked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBlocked);
            _tmpIsBlocked = _tmp != 0;
            final int _tmpTimeLimit;
            _tmpTimeLimit = _cursor.getInt(_cursorIndexOfTimeLimit);
            final int _tmpUsedTime;
            _tmpUsedTime = _cursor.getInt(_cursorIndexOfUsedTime);
            final String _tmpAllowedStartTime;
            _tmpAllowedStartTime = _cursor.getString(_cursorIndexOfAllowedStartTime);
            final String _tmpAllowedEndTime;
            _tmpAllowedEndTime = _cursor.getString(_cursorIndexOfAllowedEndTime);
            _item = new ParentalControl(_tmpId,_tmpPackageName,_tmpAppName,_tmpIsBlocked,_tmpTimeLimit,_tmpUsedTime,_tmpAllowedStartTime,_tmpAllowedEndTime);
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
  public Flow<List<ParentalControl>> getBlockedApps() {
    final String _sql = "SELECT * FROM parental_controls WHERE isBlocked = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"parental_controls"}, new Callable<List<ParentalControl>>() {
      @Override
      @NonNull
      public List<ParentalControl> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfIsBlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBlocked");
          final int _cursorIndexOfTimeLimit = CursorUtil.getColumnIndexOrThrow(_cursor, "timeLimit");
          final int _cursorIndexOfUsedTime = CursorUtil.getColumnIndexOrThrow(_cursor, "usedTime");
          final int _cursorIndexOfAllowedStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "allowedStartTime");
          final int _cursorIndexOfAllowedEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "allowedEndTime");
          final List<ParentalControl> _result = new ArrayList<ParentalControl>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ParentalControl _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final boolean _tmpIsBlocked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBlocked);
            _tmpIsBlocked = _tmp != 0;
            final int _tmpTimeLimit;
            _tmpTimeLimit = _cursor.getInt(_cursorIndexOfTimeLimit);
            final int _tmpUsedTime;
            _tmpUsedTime = _cursor.getInt(_cursorIndexOfUsedTime);
            final String _tmpAllowedStartTime;
            _tmpAllowedStartTime = _cursor.getString(_cursorIndexOfAllowedStartTime);
            final String _tmpAllowedEndTime;
            _tmpAllowedEndTime = _cursor.getString(_cursorIndexOfAllowedEndTime);
            _item = new ParentalControl(_tmpId,_tmpPackageName,_tmpAppName,_tmpIsBlocked,_tmpTimeLimit,_tmpUsedTime,_tmpAllowedStartTime,_tmpAllowedEndTime);
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
