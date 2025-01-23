package org.android.bbangzip.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.BadgeCardEntity
import org.android.bbangzip.domain.model.BadgeCardListEntity

@Serializable
data class ResponseMarkDoneDto(
    @SerialName("badges")
    val badges: List<Badge>?,
) {
    fun toBadgeCardListEntity() =
        BadgeCardListEntity(
            badgeCardList =
                badges?.map { item ->
                    item.toBadgeCardEntity()
                } ?: listOf(),
        )
}

@Serializable
data class Badge(
    @SerialName("badgeImage")
    val badgeImage: String,
    @SerialName("badgeName")
    val badgeName: String,
    @SerialName("hashTags")
    val hashTags: List<String>,
) {
    fun toBadgeCardEntity() =
        BadgeCardEntity(
            badgeImage = badgeImage,
            badgeName = badgeName,
            hashTags = hashTags,
        )
}
