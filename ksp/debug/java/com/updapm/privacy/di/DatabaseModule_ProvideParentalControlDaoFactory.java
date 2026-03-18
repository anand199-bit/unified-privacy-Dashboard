package com.updapm.privacy.di;

import com.updapm.privacy.data.local.AppDatabase;
import com.updapm.privacy.data.local.dao.ParentalControlDao;
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
public final class DatabaseModule_ProvideParentalControlDaoFactory implements Factory<ParentalControlDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideParentalControlDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ParentalControlDao get() {
    return provideParentalControlDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideParentalControlDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideParentalControlDaoFactory(databaseProvider);
  }

  public static ParentalControlDao provideParentalControlDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideParentalControlDao(database));
  }
}
