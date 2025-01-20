package org.android.bbangzip.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.bbangzip.domain.repository.remote.DummyRepository
import org.android.bbangzip.domain.repository.remote.PieceRepository
import org.android.bbangzip.domain.usecase.FetchDummyUseCase
import org.android.bbangzip.domain.usecase.GetAddTodoListUseCase
import org.android.bbangzip.domain.usecase.GetToInfoUseCase
import org.android.bbangzip.domain.usecase.PostDeletedItemList
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
    fun providesTodoInfoFetchUseCase(pieceRepository: PieceRepository): GetToInfoUseCase =
        GetToInfoUseCase(pieceRepository = pieceRepository)

    @Provides
    @Singleton
    fun providesAddTodoListFetchUseCase(pieceRepository: PieceRepository): GetAddTodoListUseCase =
        GetAddTodoListUseCase(pieceRepository = pieceRepository)

    @Provides
    @Singleton
    fun providesDeletedItemListPostUseCase(pieceRepository: PieceRepository): PostDeletedItemList =
        PostDeletedItemList(pieceRepository = pieceRepository)
}
