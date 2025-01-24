package org.android.bbangzip.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.bbangzip.domain.repository.remote.DummyRepository
import org.android.bbangzip.domain.repository.remote.ExamRepository
import org.android.bbangzip.domain.repository.remote.MyPageRepository
import org.android.bbangzip.domain.repository.remote.PieceRepository
import org.android.bbangzip.domain.repository.remote.SubjectRepository
import org.android.bbangzip.domain.repository.remote.UserRepository
import org.android.bbangzip.domain.usecase.DeleteLogoutUseCase
import org.android.bbangzip.domain.usecase.DeleteSubjectsUseCase
import org.android.bbangzip.domain.usecase.DeleteWithdrawUseCase
import org.android.bbangzip.domain.usecase.FetchBbangZipUseCase
import org.android.bbangzip.domain.usecase.FetchDummyUseCase
import org.android.bbangzip.domain.usecase.GetAddTodoListUseCase
import org.android.bbangzip.domain.usecase.GetBadgeCategoryListUseCase
import org.android.bbangzip.domain.usecase.GetBadgeDetailUseCase
import org.android.bbangzip.domain.usecase.GetSubjectDetailUseCase
import org.android.bbangzip.domain.usecase.GetSubjectInfoUseCase
import org.android.bbangzip.domain.usecase.GetToInfoUseCase
import org.android.bbangzip.domain.usecase.PostAddSubjectNameUseCase
import org.android.bbangzip.domain.usecase.PostAddTodoItemListUseCase
import org.android.bbangzip.domain.usecase.PostCompleteCardIdUseCase
import org.android.bbangzip.domain.usecase.PostDeletedItemListUseCase
import org.android.bbangzip.domain.usecase.PostOnboardingUseCase
import org.android.bbangzip.domain.usecase.PostUnCompleteCardIdUseCase
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

    @Provides
    @Singleton
    fun providesUnCompleteCardIdPostUseCase(pieceRepository: PieceRepository): PostUnCompleteCardIdUseCase =
        PostUnCompleteCardIdUseCase(pieceRepository = pieceRepository)

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

    @Provides
    @Singleton
    fun providesFetchBbangZipUseCase(myPageRepository: MyPageRepository): FetchBbangZipUseCase =
        FetchBbangZipUseCase(myPageRepository = myPageRepository)

    @Provides
    @Singleton
    fun providesGetSubjectDetailUseCase(examRepository: ExamRepository): GetSubjectDetailUseCase =
        GetSubjectDetailUseCase(examRepository = examRepository)

    @Provides
    @Singleton
    fun providesGetSubjectInfoUseCase(subjectRepository: SubjectRepository): GetSubjectInfoUseCase =
        GetSubjectInfoUseCase(subjectRepository = subjectRepository)

    @Provides
    @Singleton
    fun providesPostAddSubjectNameUseCase(subjectRepository: SubjectRepository): PostAddSubjectNameUseCase =
        PostAddSubjectNameUseCase(subjectRepository = subjectRepository)

    @Provides
    @Singleton
    fun providesDeleteSubjectsUseCase(subjectRepository: SubjectRepository): DeleteSubjectsUseCase =
        DeleteSubjectsUseCase(subjectRepository = subjectRepository)
}
