package org.android.bbangzip.data.dto.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.BadgeCategoryEntity
import org.android.bbangzip.domain.model.BadgeCategoryListEntity

@Serializable
data class ResponseBadgesDto(
    @SerialName("badgeList")
    val badgeList: List<Badge>
) {
    fun toBadgeCategoryList(): BadgeCategoryListEntity = badgeList.map { data ->
        data.toBadgeCategoryEntity()
    }.let {
        BadgeCategoryListEntity(it)
    }
}

@Serializable
data class Badge(
    @SerialName("badgeCategory")
    val badgeCategory: String,
    @SerialName("badgeImage")
    val badgeImage: String,
    @SerialName("badgeIsLocked")
    val badgeIsLocked: Boolean,
    @SerialName("badgeName")
    val badgeName: String
) {
    fun toBadgeCategoryEntity(): BadgeCategoryEntity = BadgeCategoryEntity(
        badgeCategory = badgeCategory,
        badgeImage = badgeImage,
        badgeIsLocked = badgeIsLocked,
        badgeName = badgeName
    )
}