package org.android.bbangzip.data.repositoryImpl

import kotlinx.coroutines.flow.Flow
import org.android.bbangzip.UserPreferences
import org.android.bbangzip.data.datasource.local.UserLocalDataSource
import org.android.bbangzip.domain.repository.local.UserRepository
import javax.inject.Inject

class UserRepositoryImpl
    @Inject
    constructor(
        private val userDataSource: UserLocalDataSource,
    ) : UserRepository {
        override val userPreferenceFlow: Flow<UserPreferences> = userDataSource.userPreferencesFlow

        override suspend fun setUserData(accessToken: String) {
            userDataSource.updateUserPreferences { userData ->
                userData
                    .toBuilder()
                    .setAccessToken(accessToken)
                    .build()
            }
        }

        override suspend fun clearUserData() {
            userDataSource.updateUserPreferences { userData ->
                userData
                    .toBuilder()
                    .clearAccessToken()
                    .build()
            }
        }
    }
