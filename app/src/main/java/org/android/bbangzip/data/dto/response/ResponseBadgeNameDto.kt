package org.android.bbangzip.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.BadgeDetailEntity

@Serializable
data class ResponseBadgeNameDto(
    @SerialName("achievementCondition")
    val achievementCondition: String,
    @SerialName("badgeImage")
    val badgeImage: String,
    @SerialName("badgeIsLocked")
    val badgeIsLocked: Boolean,
    @SerialName("badgeName")
    val badgeName: String,
    @SerialName("hashTags")
    val hashTags: List<String>,
    @SerialName("reward")
    val reward: Int,
) {
    fun toBadgeDetailEntity(): BadgeDetailEntity =
        BadgeDetailEntity(
            achievementCondition = achievementCondition,
            badgeImage = badgeImage,
            badgeIsLocked = badgeIsLocked,
            badgeName = badgeName,
            hashTags = hashTags,
            reward = reward,
        )
}
