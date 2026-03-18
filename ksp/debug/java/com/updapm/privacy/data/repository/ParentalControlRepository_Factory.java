package com.updapm.privacy.data.repository;

import com.updapm.privacy.data.local.dao.ParentalControlDao;
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
public final class ParentalControlRepository_Factory implements Factory<ParentalControlRepository> {
  private final Provider<ParentalControlDao> parentalControlDaoProvider;

  private final Provider<AppPermissionService> appPermissionServiceProvider;

  private final Provider<UsageStatsService> usageStatsServiceProvider;

  public ParentalControlRepository_Factory(Provider<ParentalControlDao> parentalControlDaoProvider,
      Provider<AppPermissionService> appPermissionServiceProvider,
      Provider<UsageStatsService> usageStatsServiceProvider) {
    this.parentalControlDaoProvider = parentalControlDaoProvider;
    this.appPermissionServiceProvider = appPermissionServiceProvider;
    this.usageStatsServiceProvider = usageStatsServiceProvider;
  }

  @Override
  public ParentalControlRepository get() {
    return newInstance(parentalControlDaoProvider.get(), appPermissionServiceProvider.get(), usageStatsServiceProvider.get());
  }

  public static ParentalControlRepository_Factory create(
      Provider<ParentalControlDao> parentalControlDaoProvider,
      Provider<AppPermissionService> appPermissionServiceProvider,
      Provider<UsageStatsService> usageStatsServiceProvider) {
    return new ParentalControlRepository_Factory(parentalControlDaoProvider, appPermissionServiceProvider, usageStatsServiceProvider);
  }

  public static ParentalControlRepository newInstance(ParentalControlDao parentalControlDao,
      AppPermissionService appPermissionService, UsageStatsService usageStatsService) {
    return new ParentalControlRepository(parentalControlDao, appPermissionService, usageStatsService);
  }
}
