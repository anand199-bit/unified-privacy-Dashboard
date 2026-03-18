package com.updapm.privacy.data.service;

import android.content.Context;
import com.updapm.privacy.data.repository.LogRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class PermissionAccessLogger_Factory implements Factory<PermissionAccessLogger> {
  private final Provider<Context> contextProvider;

  private final Provider<LogRepository> logRepositoryProvider;

  private final Provider<UsageStatsService> usageStatsServiceProvider;

  public PermissionAccessLogger_Factory(Provider<Context> contextProvider,
      Provider<LogRepository> logRepositoryProvider,
      Provider<UsageStatsService> usageStatsServiceProvider) {
    this.contextProvider = contextProvider;
    this.logRepositoryProvider = logRepositoryProvider;
    this.usageStatsServiceProvider = usageStatsServiceProvider;
  }

  @Override
  public PermissionAccessLogger get() {
    return newInstance(contextProvider.get(), logRepositoryProvider.get(), usageStatsServiceProvider.get());
  }

  public static PermissionAccessLogger_Factory create(Provider<Context> contextProvider,
      Provider<LogRepository> logRepositoryProvider,
      Provider<UsageStatsService> usageStatsServiceProvider) {
    return new PermissionAccessLogger_Factory(contextProvider, logRepositoryProvider, usageStatsServiceProvider);
  }

  public static PermissionAccessLogger newInstance(Context context, LogRepository logRepository,
      UsageStatsService usageStatsService) {
    return new PermissionAccessLogger(context, logRepository, usageStatsService);
  }
}
