package org.android.bbangzip.domain.repository.local

import kotlinx.coroutines.flow.Flow
import org.android.bbangzip.UserPreferences

interface UserLocalRepository {
    val userPreferenceFlow: Flow<UserPreferences>

    suspend fun setAccessToken(accessToken: String)

    suspend fun clearAccessToken()

    suspend fun setRefreshToken(refreshToken: String)

    suspend fun clearRefreshToken()

    suspend fun setIsLogin(isLogin: Boolean)

    suspend fun setOnboardingInfo(
        userName: String,
        year: Int,
        semester: String,
        subject: String,
    )

    suspend fun clearOnboardingInfo()
}
