package com.updapm.privacy.presentation.permissions;

import com.updapm.privacy.data.repository.LogRepository;
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
public final class PermissionViewModel_Factory implements Factory<PermissionViewModel> {
  private final Provider<PermissionRepository> permissionRepositoryProvider;

  private final Provider<LogRepository> logRepositoryProvider;

  public PermissionViewModel_Factory(Provider<PermissionRepository> permissionRepositoryProvider,
      Provider<LogRepository> logRepositoryProvider) {
    this.permissionRepositoryProvider = permissionRepositoryProvider;
    this.logRepositoryProvider = logRepositoryProvider;
  }

  @Override
  public PermissionViewModel get() {
    return newInstance(permissionRepositoryProvider.get(), logRepositoryProvider.get());
  }

  public static PermissionViewModel_Factory create(
      Provider<PermissionRepository> permissionRepositoryProvider,
      Provider<LogRepository> logRepositoryProvider) {
    return new PermissionViewModel_Factory(permissionRepositoryProvider, logRepositoryProvider);
  }

  public static PermissionViewModel newInstance(PermissionRepository permissionRepository,
      LogRepository logRepository) {
    return new PermissionViewModel(permissionRepository, logRepository);
  }
}
