package org.android.bbangzip.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.ReissueEntity

@Serializable
data class ResponseReissueDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String
) {
    fun toReissueEntity() =
        ReissueEntity(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
}
