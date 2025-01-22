package org.android.bbangzip.domain.model

data class BadgeDetailEntity(
    val achievementCondition: String,
    val badgeImage: String,
    val badgeIsLocked: Boolean,
    val badgeName: String,
    val hashTags: List<String>,
    val reward: Int,
)
