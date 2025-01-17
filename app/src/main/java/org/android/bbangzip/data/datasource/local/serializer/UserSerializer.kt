package org.android.bbangzip.data.datasource.local.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import org.android.bbangzip.UserPreferences
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences
        get() = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        try {
            return UserPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot Read proto", exception)
        }
    }

    override suspend fun writeTo(
        t: UserPreferences,
        output: OutputStream,
    ) = t.writeTo(output)
}
