package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.domain.model.BadgeCategoryListEntity
import org.android.bbangzip.domain.model.BadgeDetailEntity
import org.android.bbangzip.domain.model.MyEntity

interface MyPageRepository {
    suspend fun getBadgeCategoryList(): Result<BadgeCategoryListEntity>

    suspend fun getBadgeDetail(
        badgeName: String,
    ): Result<BadgeDetailEntity>

    suspend fun fetchMyInfo(): Result<MyEntity>
}
