package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.BadgeDetailEntity
import org.android.bbangzip.domain.repository.remote.MyPageRepository

class GetBadgeDetailUseCase(
    private val myPageRepository: MyPageRepository,
) {
    suspend operator fun invoke(badgeName: String): Result<BadgeDetailEntity> =
        myPageRepository.getBadgeDetail(badgeName = badgeName)
}
