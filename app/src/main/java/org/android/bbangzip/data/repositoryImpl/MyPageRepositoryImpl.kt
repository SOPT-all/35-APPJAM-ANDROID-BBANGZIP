package org.android.bbangzip.data.repositoryImpl

import org.android.bbangzip.data.datasource.remote.BadgesNameRemoteDataSource
import org.android.bbangzip.data.datasource.remote.BadgesRemoteDataSource
import org.android.bbangzip.domain.model.BadgeCategoryListEntity
import org.android.bbangzip.domain.model.BadgeDetailEntity
import org.android.bbangzip.domain.repository.remote.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl
    @Inject
    constructor(
        private val badgesNameRemoteDataSource: BadgesNameRemoteDataSource,
        private val badgesRemoteDataSource: BadgesRemoteDataSource,
    ) : MyPageRepository {
        override suspend fun getBadgeCategoryList(): Result<BadgeCategoryListEntity> =
            runCatching {
                val response = badgesRemoteDataSource.getBadgeCategoryList()

                val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")

                responseData.toBadgeCategoryList()
            }

        override suspend fun getBadgeDetail(badgeName: String): Result<BadgeDetailEntity> =
            runCatching {
                val response = badgesNameRemoteDataSource.getBadgeDetail(badgeName)

                val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")

                responseData.toBadgeDetailEntity()
            }
    }
