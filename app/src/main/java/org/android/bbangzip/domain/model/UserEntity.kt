package org.android.bbangzip.domain.model

data class UserEntity(
    val accessToken: String,
    val refreshToken: String,
    val isOnboardingComplete: Boolean,
)
