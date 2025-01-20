package org.android.bbangzip.domain.model

data class BadgeCardListEntity(
   val badgeCardList : List <BadgeCardEntity>
)

data class BadgeCardEntity(
    val badgeImage: String,
    val badgeName: String,
    val hashTags: List<String>
)


