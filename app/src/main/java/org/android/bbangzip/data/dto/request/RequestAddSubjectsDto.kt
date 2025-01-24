package org.android.bbangzip.data.dto.request

import kotlinx.serialization.SerialName

data class RequestAddSubjectsDto(
    @SerialName("subjectName")
    val subjectName: String,
    @SerialName("year")
    val year: Int,
    @SerialName("semester")
    val semester: String
)
