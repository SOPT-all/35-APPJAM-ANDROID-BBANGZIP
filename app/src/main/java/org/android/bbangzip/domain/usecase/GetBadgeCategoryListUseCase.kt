package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.BadgeCategoryListEntity
import org.android.bbangzip.domain.repository.remote.MyPageRepository

class GetBadgeCategoryListUseCase(
    private val myPageRepository: MyPageRepository,
) {
    suspend operator fun invoke(): Result<BadgeCategoryListEntity> =
        myPageRepository.getBadgeCategoryList()
}
