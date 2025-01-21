package org.android.bbangzip.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.UserEntity

@Serializable
data class ResponseUserDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
    @SerialName("isOnboardingComplete")
    val isOnboardingComplete: Boolean,
) {
    fun toUserEntity() =
        UserEntity(
            accessToken = accessToken,
            refreshToken = refreshToken,
            isOnboardingComplete = isOnboardingComplete,
        )
}
