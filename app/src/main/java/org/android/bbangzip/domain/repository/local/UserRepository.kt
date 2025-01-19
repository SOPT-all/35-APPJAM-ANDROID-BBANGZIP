package org.android.bbangzip.domain.repository.local

import kotlinx.coroutines.flow.Flow
import org.android.bbangzip.OnboardingInfo
import org.android.bbangzip.UserPreferences

interface UserRepository {
    val userPreferenceFlow: Flow<UserPreferences>

    suspend fun setUserData(accessToken: String)

    suspend fun clearUserData()

    suspend fun setOnboardingInfo(onboardingInfo: OnboardingInfo)

    suspend fun clearOnboardingInfo()
}
