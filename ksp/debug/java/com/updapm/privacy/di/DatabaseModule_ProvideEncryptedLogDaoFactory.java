package com.updapm.privacy.di;

import com.updapm.privacy.data.local.AppDatabase;
import com.updapm.privacy.data.local.dao.EncryptedLogDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DatabaseModule_ProvideEncryptedLogDaoFactory implements Factory<EncryptedLogDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideEncryptedLogDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public EncryptedLogDao get() {
    return provideEncryptedLogDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideEncryptedLogDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideEncryptedLogDaoFactory(databaseProvider);
  }

  public static EncryptedLogDao provideEncryptedLogDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideEncryptedLogDao(database));
  }
}
