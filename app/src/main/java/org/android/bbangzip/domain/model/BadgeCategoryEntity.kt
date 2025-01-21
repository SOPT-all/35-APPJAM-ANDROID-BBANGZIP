package org.android.bbangzip.domain.model

import kotlinx.serialization.SerialName

data class BadgeCategoryListEntity(
    val badgeList: List<BadgeCategoryEntity>
)

data class BadgeCategoryEntity(
    val badgeCategory: String,
    val badgeImage: String,
    val badgeIsLocked: Boolean,
    val badgeName: String
)
