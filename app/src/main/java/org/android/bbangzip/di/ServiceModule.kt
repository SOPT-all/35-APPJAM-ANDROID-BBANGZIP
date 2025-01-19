package org.android.bbangzip.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.bbangzip.data.service.DummyService
import org.android.bbangzip.data.service.PieceService
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
    fun providePieceService(
        @BbangZip retrofit: Retrofit,
    ): PieceService =
        retrofit.create(PieceService::class.java)
}
