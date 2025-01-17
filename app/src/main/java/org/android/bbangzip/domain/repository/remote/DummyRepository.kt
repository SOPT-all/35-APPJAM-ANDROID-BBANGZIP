package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.domain.model.DummyEntity

interface DummyRepository {
    suspend fun fetchDummy(): Result<DummyEntity>
}
