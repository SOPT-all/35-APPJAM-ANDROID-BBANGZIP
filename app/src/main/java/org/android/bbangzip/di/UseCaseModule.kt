package org.android.bbangzip.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.bbangzip.domain.repository.remote.DummyRepository
import org.android.bbangzip.domain.repository.remote.MyPageRepository
import org.android.bbangzip.domain.usecase.FetchDummyUseCase
import org.android.bbangzip.domain.usecase.GetBadgeCategoryListUseCase
import org.android.bbangzip.domain.usecase.GetBadgeDetailUseCase
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
    fun providesBadgeCategoryListGetUseCase(myPageRepository: MyPageRepository): GetBadgeCategoryListUseCase =
        GetBadgeCategoryListUseCase(myPageRepository = myPageRepository)

    @Provides
    @Singleton
    fun providesBadgeDetailGetUseCase(myPageRepository: MyPageRepository): GetBadgeDetailUseCase =
        GetBadgeDetailUseCase(myPageRepository = myPageRepository)
}
