package org.android.bbangzip.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.bbangzip.data.service.DummyService
import org.android.bbangzip.data.service.ExamService
import org.android.bbangzip.data.service.MyPageService
import org.android.bbangzip.data.service.PieceService
import org.android.bbangzip.data.service.SubjectService
import org.android.bbangzip.data.service.StudyService
import org.android.bbangzip.data.service.UserService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideDummyService(
        @BbangZip retrofit: Retrofit,
    ): DummyService =
        retrofit.create(DummyService::class.java)

    @Provides
    @Singleton
    fun provideMyPageService(
        @BbangZip retrofit: Retrofit,
    ): MyPageService =
        retrofit.create(MyPageService::class.java)

    @Provides
    @Singleton
    fun providePieceService(
        @BbangZip retrofit: Retrofit,
    ): PieceService =
        retrofit.create(PieceService::class.java)

    @Provides
    @Singleton
    fun provideUserService(
        @BbangZip retrofit: Retrofit,
    ): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideExamService(
        @BbangZip retrofit: Retrofit,
    ): ExamService =
        retrofit.create(ExamService::class.java)

    @Provides
    @Singleton
    fun provideSubjectService(
        @BbangZip retrofit: Retrofit,
    ): SubjectService =
        retrofit.create(SubjectService::class.java)

    @Provides
    @Singleton
    fun provideStudyService(
        @BbangZip retrofit: Retrofit
    ): StudyService =
        retrofit.create(StudyService::class.java)
}
