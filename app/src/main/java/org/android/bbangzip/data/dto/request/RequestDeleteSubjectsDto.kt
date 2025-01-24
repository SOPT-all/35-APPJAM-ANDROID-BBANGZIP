package org.android.bbangzip.data.dto.request

import kotlinx.serialization.SerialName

data class RequestDeleteSubjectsDto(
    @SerialName("subjectIds")
    val subjectIds: List<Int>,
    @SerialName("year")
    val year: Int,
    @SerialName("semester")
    val semester: String)
