package org.android.bbangzip.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestHideDto(
    @SerialName("pieceIds")
    val pieceIds: List<Int>,
)
