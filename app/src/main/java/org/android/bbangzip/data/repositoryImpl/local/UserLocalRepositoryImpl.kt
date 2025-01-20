package org.android.bbangzip.data.repositoryImpl.local

import kotlinx.coroutines.flow.Flow
import org.android.bbangzip.OnboardingInfo
import org.android.bbangzip.UserPreferences
import org.android.bbangzip.data.datasource.local.UserLocalDataSource
import org.android.bbangzip.domain.repository.local.UserLocalRepository
import javax.inject.Inject

class UserLocalRepositoryImpl
@Inject
constructor(
    private val userDataSource: UserLocalDataSource,
) : UserLocalRepository {
    override val userPreferenceFlow: Flow<UserPreferences> = userDataSource.userPreferencesFlow

    override suspend fun setAccessToken(accessToken: String) {
        userDataSource.updateUserPreferences { userData ->
            userData
                .toBuilder()
                .setAccessToken(accessToken)
                .build()
        }
    }

    override suspend fun clearAccessToken() {
        userDataSource.updateUserPreferences { userData ->
            userData
                .toBuilder()
                .clearAccessToken()
                .clearOnboardingInfo()
                .build()
        }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        userDataSource.updateUserPreferences { userData ->
            userData
                .toBuilder()
                .setRefreshToken(refreshToken)
                .build()
        }
    }

    override suspend fun clearRefreshToken() {
        userDataSource.updateUserPreferences { userData ->
            userData
                .toBuilder()
                .clearRefreshToken()
                .build()
        }
    }

    override suspend fun setIsLogin(isLogin: Boolean) {
        userDataSource.updateUserPreferences { userData ->
            userData
                .toBuilder()
                .setIsLogin(isLogin)
                .build()
        }
    }

    override suspend fun setOnboardingInfo(
        userName: String,
        year: Int,
        semester: String,
        subject: String,
    ) {
        userDataSource.updateUserPreferences { userData ->
            val onboardingInfo =
                OnboardingInfo.newBuilder()
                    .setUserName(userName)
                    .setYear(year)
                    .setSemester(semester)
                    .setSubjectName(subject)
                    .build()

            userData
                .toBuilder()
                .setOnboardingInfo(onboardingInfo)
                .build()
        }
    }

    override suspend fun clearOnboardingInfo() {
        userDataSource.updateUserPreferences { userData ->
            userData
                .toBuilder()
                .clearOnboardingInfo()
                .build()
        }
    }
}
