package org.android.bbangzip.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.bbangzip.domain.repository.remote.DummyRepository
import org.android.bbangzip.domain.repository.remote.UserRepository
import org.android.bbangzip.domain.usecase.DeleteLogoutUseCase
import org.android.bbangzip.domain.usecase.DeleteWithdrawUseCase
import org.android.bbangzip.domain.usecase.FetchDummyUseCase
import org.android.bbangzip.domain.usecase.PostOnboardingUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun providesDummyFetchUseCase(dummyRepository: DummyRepository): FetchDummyUseCase =
        FetchDummyUseCase(dummyRepository = dummyRepository)

    @Provides
    @Singleton
    fun providesPostOnboardingUseCase(userRepository: UserRepository): PostOnboardingUseCase =
        PostOnboardingUseCase(userRepository = userRepository)

    @Provides
    @Singleton
    fun providesDeleteLogoutUseCase(userRepository: UserRepository): DeleteLogoutUseCase =
        DeleteLogoutUseCase(userRepository = userRepository)

    @Provides
    @Singleton
    fun providesDeleteWithdrawUseCase(userRepository: UserRepository): DeleteWithdrawUseCase =
        DeleteWithdrawUseCase(userRepository = userRepository)
}
