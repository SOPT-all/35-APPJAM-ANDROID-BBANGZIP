package org.android.bbangzip.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSubjectOptions(
    @SerialName("value")
    val value: String,
)
