package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BadgeDetail(
    val name: String = "",
    val categoryName: String = "",
    val imageUrl: String = "",
    val hashTags: List<String> = listOf(),
    val achievementCondition: String = "",
    val reward: Int = 0,
    val isLocked: Boolean = false,
) : Parcelable
