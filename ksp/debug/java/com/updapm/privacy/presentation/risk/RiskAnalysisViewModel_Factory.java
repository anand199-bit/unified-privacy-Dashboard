package com.updapm.privacy.presentation.risk;

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
public final class RiskAnalysisViewModel_Factory implements Factory<RiskAnalysisViewModel> {
  private final Provider<PermissionRepository> permissionRepositoryProvider;

  public RiskAnalysisViewModel_Factory(
      Provider<PermissionRepository> permissionRepositoryProvider) {
    this.permissionRepositoryProvider = permissionRepositoryProvider;
  }

  @Override
  public RiskAnalysisViewModel get() {
    return newInstance(permissionRepositoryProvider.get());
  }

  public static RiskAnalysisViewModel_Factory create(
      Provider<PermissionRepository> permissionRepositoryProvider) {
    return new RiskAnalysisViewModel_Factory(permissionRepositoryProvider);
  }

  public static RiskAnalysisViewModel newInstance(PermissionRepository permissionRepository) {
    return new RiskAnalysisViewModel(permissionRepository);
  }
}
