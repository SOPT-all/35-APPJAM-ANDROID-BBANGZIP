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
import org.android.bbangzip.domain.usecase.PostAddTodoItemListUseCase
import org.android.bbangzip.domain.usecase.PostCompleteCardIdUseCase
import org.android.bbangzip.domain.usecase.PostDeletedItemListUseCase
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
    fun providesDeletedItemListPostUseCase(pieceRepository: PieceRepository): PostDeletedItemListUseCase =
        PostDeletedItemListUseCase(pieceRepository = pieceRepository)

    @Provides
    @Singleton
    fun providesAddTodoItemListPostUseCase(pieceRepository: PieceRepository): PostAddTodoItemListUseCase =
        PostAddTodoItemListUseCase(pieceRepository = pieceRepository)

    @Provides
    @Singleton
    fun providesCompleteCardIdPostUseCase(pieceRepository: PieceRepository): PostCompleteCardIdUseCase =
        PostCompleteCardIdUseCase(pieceRepository = pieceRepository)
}
