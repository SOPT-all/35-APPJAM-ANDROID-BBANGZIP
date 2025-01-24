package org.android.bbangzip.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestAddSubjectsDto(
    @SerialName("subjectName")
    val subjectName: String,
    @SerialName("year")
    val year: Int,
    @SerialName("semester")
    val semester: String,
)
