package org.android.bbangzip.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import org.android.bbangzip.UserPreferences
import org.android.bbangzip.data.util.context.userDataSource
import javax.inject.Inject

class UserLocalDataSource
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) {
        private val dataStore: DataStore<UserPreferences> = context.userDataSource

        val userPreferencesFlow: Flow<UserPreferences> = dataStore.data

        suspend fun updateUserPreferences(update: (UserPreferences) -> UserPreferences) {
            dataStore.updateData(update)
        }
    }
