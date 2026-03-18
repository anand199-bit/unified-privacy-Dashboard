package com.updapm.privacy.presentation.dashboard;

import com.updapm.privacy.data.repository.PermissionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<PermissionRepository> permissionRepositoryProvider;

  public DashboardViewModel_Factory(Provider<PermissionRepository> permissionRepositoryProvider) {
    this.permissionRepositoryProvider = permissionRepositoryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(permissionRepositoryProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<PermissionRepository> permissionRepositoryProvider) {
    return new DashboardViewModel_Factory(permissionRepositoryProvider);
  }

  public static DashboardViewModel newInstance(PermissionRepository permissionRepository) {
    return new DashboardViewModel(permissionRepository);
  }
}
