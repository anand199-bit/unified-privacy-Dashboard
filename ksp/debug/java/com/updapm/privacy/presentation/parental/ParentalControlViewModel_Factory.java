package com.updapm.privacy.presentation.parental;

import com.updapm.privacy.data.repository.ParentalControlRepository;
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
public final class ParentalControlViewModel_Factory implements Factory<ParentalControlViewModel> {
  private final Provider<ParentalControlRepository> parentalControlRepositoryProvider;

  public ParentalControlViewModel_Factory(
      Provider<ParentalControlRepository> parentalControlRepositoryProvider) {
    this.parentalControlRepositoryProvider = parentalControlRepositoryProvider;
  }

  @Override
  public ParentalControlViewModel get() {
    return newInstance(parentalControlRepositoryProvider.get());
  }

  public static ParentalControlViewModel_Factory create(
      Provider<ParentalControlRepository> parentalControlRepositoryProvider) {
    return new ParentalControlViewModel_Factory(parentalControlRepositoryProvider);
  }

  public static ParentalControlViewModel newInstance(
      ParentalControlRepository parentalControlRepository) {
    return new ParentalControlViewModel(parentalControlRepository);
  }
}
