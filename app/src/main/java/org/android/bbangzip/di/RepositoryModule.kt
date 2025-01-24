package org.android.bbangzip.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.bbangzip.data.repositoryImpl.local.UserLocalRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.remote.DummyRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.remote.ExamRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.remote.MyPageRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.remote.PieceRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.remote.StudyRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.remote.SubjectRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.remote.UserRepositoryImpl
import org.android.bbangzip.domain.repository.local.UserLocalRepository
import org.android.bbangzip.domain.repository.remote.DummyRepository
import org.android.bbangzip.domain.repository.remote.ExamRepository
import org.android.bbangzip.domain.repository.remote.MyPageRepository
import org.android.bbangzip.domain.repository.remote.PieceRepository
import org.android.bbangzip.domain.repository.remote.StudyRepository
import org.android.bbangzip.domain.repository.remote.SubjectRepository
import org.android.bbangzip.domain.repository.remote.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsDummyRepository(repositoryImpl: DummyRepositoryImpl): DummyRepository

    @Binds
    @Singleton
    abstract fun bindsUserLocalRepository(repositoryImpl: UserLocalRepositoryImpl): UserLocalRepository

    @Binds
    @Singleton
    abstract fun bindsUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindsPieceRepository(repositoryImpl: PieceRepositoryImpl): PieceRepository

    @Binds
    @Singleton
    abstract fun bindsMyPageRepository(repositoryImpl: MyPageRepositoryImpl): MyPageRepository

    @Binds
    @Singleton
    abstract fun bindsExamRepository(repositoryImpl: ExamRepositoryImpl): ExamRepository

    @Binds
    @Singleton
    abstract fun bindsSubjectRepository(repositoryImpl: SubjectRepositoryImpl): SubjectRepository

    @Binds
    @Singleton
    abstract fun bindsServiceRepository(repositoryImpl: StudyRepositoryImpl): StudyRepository
}
