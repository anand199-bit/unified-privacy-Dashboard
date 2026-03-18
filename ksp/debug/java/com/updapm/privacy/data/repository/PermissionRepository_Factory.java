package com.updapm.privacy.data.repository;

import com.updapm.privacy.data.local.dao.AppPermissionDao;
import com.updapm.privacy.data.service.AppPermissionService;
import com.updapm.privacy.data.service.UsageStatsService;
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
public final class PermissionRepository_Factory implements Factory<PermissionRepository> {
  private final Provider<AppPermissionDao> appPermissionDaoProvider;

  private final Provider<AppPermissionService> appPermissionServiceProvider;

  private final Provider<UsageStatsService> usageStatsServiceProvider;

  public PermissionRepository_Factory(Provider<AppPermissionDao> appPermissionDaoProvider,
      Provider<AppPermissionService> appPermissionServiceProvider,
      Provider<UsageStatsService> usageStatsServiceProvider) {
    this.appPermissionDaoProvider = appPermissionDaoProvider;
    this.appPermissionServiceProvider = appPermissionServiceProvider;
    this.usageStatsServiceProvider = usageStatsServiceProvider;
  }

  @Override
  public PermissionRepository get() {
    return newInstance(appPermissionDaoProvider.get(), appPermissionServiceProvider.get(), usageStatsServiceProvider.get());
  }

  public static PermissionRepository_Factory create(
      Provider<AppPermissionDao> appPermissionDaoProvider,
      Provider<AppPermissionService> appPermissionServiceProvider,
      Provider<UsageStatsService> usageStatsServiceProvider) {
    return new PermissionRepository_Factory(appPermissionDaoProvider, appPermissionServiceProvider, usageStatsServiceProvider);
  }

  public static PermissionRepository newInstance(AppPermissionDao appPermissionDao,
      AppPermissionService appPermissionService, UsageStatsService usageStatsService) {
    return new PermissionRepository(appPermissionDao, appPermissionService, usageStatsService);
  }
}
