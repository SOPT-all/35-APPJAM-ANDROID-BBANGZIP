package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.MyEntity
import org.android.bbangzip.domain.repository.remote.MyPageRepository

class FetchBbangZipUseCase(
    private val myPageRepository: MyPageRepository,
) {
    suspend operator fun invoke(): Result<MyEntity> =
        myPageRepository.fetchMyInfo()
}
