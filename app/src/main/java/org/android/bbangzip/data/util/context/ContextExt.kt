package org.android.bbangzip.data.util.context

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import org.android.bbangzip.UserPreferences
import org.android.bbangzip.data.datasource.local.serializer.UserSerializer

val Context.userDataSource: DataStore<UserPreferences> by dataStore(
    fileName = "user_prefs.proto",
    serializer = UserSerializer,
)
