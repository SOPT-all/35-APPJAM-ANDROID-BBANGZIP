package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.domain.model.OnboardingEntity
import org.android.bbangzip.domain.model.ReissueEntity
import org.android.bbangzip.domain.model.UserEntity

interface UserRepository {
    suspend fun login(code: String): Result<UserEntity>
    suspend fun reissue(): Result<ReissueEntity>
    suspend fun logout(): Result<String>
    suspend fun withdraw(): Result<String>
    suspend fun onboardingComplete(onboardingEntity: OnboardingEntity): Result<String>
}
