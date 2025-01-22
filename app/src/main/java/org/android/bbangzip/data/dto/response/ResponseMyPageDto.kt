package org.android.bbangzip.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.BbangZipEntity
import org.android.bbangzip.domain.model.MyEntity

@Serializable
data class ResponseMyPageDto(
    @SerialName("level")
    val level: Int,
    @SerialName("badgeCounts")
    val badgeCounts: Int,
    @SerialName("reward")
    val reward: Int,
    @SerialName("maxReward")
    val maxReward: Int,
    @SerialName("levelDetails")
    val levelDetails: List<BbangZipLevel>
) {
    @Serializable
    data class BbangZipLevel(
        @SerialName("level")
        val level: Int,
        @SerialName("levelName")
        val levelName: String,
        @SerialName("levelDescription")
        val levelDescription: String,
        @SerialName("levelImage")
        val levelImage: String,
        @SerialName("levelIsLocked")
        val levelIsLocked: Boolean
    ) {
        fun toMyEntity() = BbangZipEntity(
            level = level,
            levelName = levelName,
            levelDescription = levelDescription,
            levelImage = levelImage,
            levelIsLocked = levelIsLocked
        )
    }

    fun toMyPageEntity() = MyEntity(
        level = level,
        badgeCounts = badgeCounts,
        reward = reward,
        maxReward = maxReward,
        levelDetails = levelDetails.map { it.toMyEntity() }
    )
}
