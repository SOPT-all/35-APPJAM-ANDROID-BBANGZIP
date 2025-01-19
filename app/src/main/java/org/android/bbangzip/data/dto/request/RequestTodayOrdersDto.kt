package org.android.bbangzip.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestTodayOrdersDto(
    @SerialName("area")
    val area: String,
    @SerialName("year")
    val year: Int,
    @SerialName("semester")
    val semester: String,
    @SerialName("sortOption")
    val sortOption: String,
)
