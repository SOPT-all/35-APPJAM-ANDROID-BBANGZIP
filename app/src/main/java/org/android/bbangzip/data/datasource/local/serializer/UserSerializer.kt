package org.android.bbangzip.data.datasource.local.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import org.android.bbangzip.UserPreferences
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences
        get() = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        return try {
            val data = input.readBytes()
            Timber.d("[Proto DataStore]: ${data.decodeToString()}")
            UserPreferences.parseFrom(data)
        } catch (exception: Exception) {
            Timber.e("[Proto DataStore]: $exception")
            throw CorruptionException("Proto 데이터를 읽을 수 없습니다.", exception)
        }
    }

    override suspend fun writeTo(
        t: UserPreferences,
        output: OutputStream,
    ) = t.writeTo(output)
}
