package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyBbangZip(
    val bbangZipName: String? = null,
    val bbangZipLevel: Int? = null,
    val reward: Int? = null,
    val maxReward: Int? = null,
    val bbangZipImgUrl: String? = null,
    val badgeCount: Int = 0
) : Parcelable
