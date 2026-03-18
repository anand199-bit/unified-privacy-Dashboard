package com.updapm.privacy.data.repository;

import com.updapm.privacy.data.local.dao.EncryptedLogDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class LogRepository_Factory implements Factory<LogRepository> {
  private final Provider<EncryptedLogDao> encryptedLogDaoProvider;

  public LogRepository_Factory(Provider<EncryptedLogDao> encryptedLogDaoProvider) {
    this.encryptedLogDaoProvider = encryptedLogDaoProvider;
  }

  @Override
  public LogRepository get() {
    return newInstance(encryptedLogDaoProvider.get());
  }

  public static LogRepository_Factory create(Provider<EncryptedLogDao> encryptedLogDaoProvider) {
    return new LogRepository_Factory(encryptedLogDaoProvider);
  }

  public static LogRepository newInstance(EncryptedLogDao encryptedLogDao) {
    return new LogRepository(encryptedLogDao);
  }
}
