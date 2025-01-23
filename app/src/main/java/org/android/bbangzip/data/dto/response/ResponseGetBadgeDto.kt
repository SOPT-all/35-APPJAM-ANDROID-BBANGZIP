package org.android.bbangzip.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.GetBadgeEntity

@Serializable
data class ResponseGetBadgeDto(
    @SerialName("badges")
    val badges: List<GetBadgeDto>
) {
    @Serializable
    data class GetBadgeDto(
        @SerialName("badgeName")
        val badgeName: String,
        @SerialName("badgeImage")
        val badgeImg: String,
        @SerialName("hashTags")
        val hashTags: List<String>
    ) {
        fun toBadgeEntity() = GetBadgeEntity.Badge(
            badgeName = badgeName,
            badgeImg = badgeImg,
            hashTags = hashTags
        )
    }

    fun toGetBadgeEntity() = GetBadgeEntity(
        badgesList = badges.map { it.toBadgeEntity() }
    )
}
