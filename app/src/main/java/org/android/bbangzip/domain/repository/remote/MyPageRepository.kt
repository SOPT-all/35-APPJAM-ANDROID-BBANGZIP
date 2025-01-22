package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.domain.model.MyEntity

interface MyPageRepository {
    suspend fun fetchMyInfo(): Result<MyEntity>
}