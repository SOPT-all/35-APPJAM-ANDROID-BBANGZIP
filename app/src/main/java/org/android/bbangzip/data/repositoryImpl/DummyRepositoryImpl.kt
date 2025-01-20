package org.android.bbangzip.data.repositoryImpl

import org.android.bbangzip.data.datasource.remote.DummyRemoteDataSource
import org.android.bbangzip.domain.model.DummyEntity
import org.android.bbangzip.domain.repository.remote.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl
    @Inject
    constructor(
        private val dummyRemoteDataSource: DummyRemoteDataSource,
    ) : DummyRepository {
        override suspend fun fetchDummy(): Result<DummyEntity?> =
            runCatching {
                dummyRemoteDataSource.getDummyData().data?.toDummyEntity()
            }
    }
