package org.android.bbangzip.domain.model

data class MyEntity(
    val level: Int,
    val badgeCounts: Int,
    val reward: Int,
    val maxReward: Int,
    val levelDetails: List<BbangZipEntity>
)