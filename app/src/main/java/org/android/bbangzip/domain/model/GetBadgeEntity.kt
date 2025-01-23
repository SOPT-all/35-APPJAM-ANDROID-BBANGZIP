package org.android.bbangzip.domain.model

data class GetBadgeEntity (
    val badgesList: List<Badge>
) {
    data class Badge(
        val badgeName: String,
        val badgeImg: String,
        val hashTags: List<String>
    )
}