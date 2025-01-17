package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.DummyEntity
import org.android.bbangzip.domain.repository.remote.DummyRepository

class FetchDummyUseCase(
    private val dummyRepository: DummyRepository,
) {
    suspend operator fun invoke(): Result<DummyEntity> =
        dummyRepository.fetchDummy()
}
