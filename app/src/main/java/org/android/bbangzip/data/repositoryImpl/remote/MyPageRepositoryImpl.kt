package org.android.bbangzip.data.repositoryImpl.remote

import org.android.bbangzip.data.datasource.remote.MyPageDataSource
import org.android.bbangzip.domain.model.MyEntity
import org.android.bbangzip.domain.repository.remote.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val myPageDataSource: MyPageDataSource
) : MyPageRepository {
    override suspend fun fetchMyInfo(): Result<MyEntity> =
        runCatching {
            val response = myPageDataSource.getMyPageInfo()
            val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")
            responseData.toMyPageEntity()
        }
}