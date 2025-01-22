package org.android.bbangzip.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.bbangzip.data.repositoryImpl.DummyRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.PieceRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.UserRepositoryImpl
import org.android.bbangzip.domain.repository.local.UserRepository
import org.android.bbangzip.data.repositoryImpl.local.UserLocalRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.remote.DummyRepositoryImpl
import org.android.bbangzip.data.repositoryImpl.remote.UserRepositoryImpl
import org.android.bbangzip.domain.repository.local.UserLocalRepository
import org.android.bbangzip.domain.repository.remote.DummyRepository
import org.android.bbangzip.domain.repository.remote.PieceRepository
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
}
