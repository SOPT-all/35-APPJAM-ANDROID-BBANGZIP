package org.android.bbangzip.domain.model

data class BadgeCategoryListEntity(
    val badgeList: List<BadgeCategoryEntity>,
)

data class BadgeCategoryEntity(
    val badgeCategory: String,
    val badgeImage: String,
    val badgeIsLocked: Boolean,
    val badgeName: String,
)
