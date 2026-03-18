package com.updapm.privacy;

import com.updapm.privacy.data.service.PermissionMonitorService;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<PermissionMonitorService> permissionMonitorServiceProvider;

  public MainActivity_MembersInjector(
      Provider<PermissionMonitorService> permissionMonitorServiceProvider) {
    this.permissionMonitorServiceProvider = permissionMonitorServiceProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<PermissionMonitorService> permissionMonitorServiceProvider) {
    return new MainActivity_MembersInjector(permissionMonitorServiceProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectPermissionMonitorService(instance, permissionMonitorServiceProvider.get());
  }

  @InjectedFieldSignature("com.updapm.privacy.MainActivity.permissionMonitorService")
  public static void injectPermissionMonitorService(MainActivity instance,
      PermissionMonitorService permissionMonitorService) {
    instance.permissionMonitorService = permissionMonitorService;
  }
}
