package org.android.bbangzip.domain.repository

import org.android.bbangzip.domain.model.DummyEntity

interface DummyRepository {
    suspend fun fetchDummy(): Result<DummyEntity>
}