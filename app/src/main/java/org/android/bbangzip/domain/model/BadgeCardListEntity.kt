package org.android.bbangzip.domain.model

import org.android.bbangzip.presentation.model.Badge

data class BadgeCardListEntity(
    val badgeCardList: List<BadgeCardEntity>,
)

data class BadgeCardEntity(
    val badgeImage: String,
    val badgeName: String,
    val hashTags: List<String>,
) {
    fun toBadge() =
        Badge(
            badgeName = badgeName,
            badgeImg = badgeImage,
            hashTags = hashTags,
        )
}
