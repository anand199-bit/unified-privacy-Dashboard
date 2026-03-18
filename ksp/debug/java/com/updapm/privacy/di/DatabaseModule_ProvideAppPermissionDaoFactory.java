package com.updapm.privacy.di;

import com.updapm.privacy.data.local.AppDatabase;
import com.updapm.privacy.data.local.dao.AppPermissionDao;
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
public final class DatabaseModule_ProvideAppPermissionDaoFactory implements Factory<AppPermissionDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideAppPermissionDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AppPermissionDao get() {
    return provideAppPermissionDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideAppPermissionDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideAppPermissionDaoFactory(databaseProvider);
  }

  public static AppPermissionDao provideAppPermissionDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAppPermissionDao(database));
  }
}
