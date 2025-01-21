package org.android.bbangzip.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestOnboardingDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("year")
    val year: Int,
    @SerialName("semester")
    val semester: String,
    @SerialName("subjectName")
    val subjectName: String,
)
