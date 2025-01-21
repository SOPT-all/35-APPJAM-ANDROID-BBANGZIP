package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.data.util.base.BaseResponse
import org.android.bbangzip.domain.model.BadgeCategoryListEntity
import org.android.bbangzip.domain.model.BadgeDetailEntity

interface MyPageRepository {
    suspend fun getBadgeCategoryList(): Result<BadgeCategoryListEntity>

    suspend fun getBadgeDetail(
        badgeName: String
    ): Result<BadgeDetailEntity>
}