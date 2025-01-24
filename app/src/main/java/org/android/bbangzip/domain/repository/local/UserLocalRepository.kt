package org.android.bbangzip.domain.repository.local

import kotlinx.coroutines.flow.Flow
import org.android.bbangzip.UserPreferences
import org.android.bbangzip.presentation.model.Badge

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

    suspend fun setIsOnboardingCompleted(isOnboardingCompleted: Boolean)

    suspend fun clearOnboardingInfo()

    suspend fun setIsOnOnboardingDone(isOnboardingDone: Boolean)

    suspend fun setIsBadgeAvailable(isBadgeAvailable: Boolean)

    suspend fun setBadgeInfo(
        badgeName: String,
        badgeImage: String,
        hashTags: List<String>
    )
}
