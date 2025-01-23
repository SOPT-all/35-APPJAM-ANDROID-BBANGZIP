package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Badge(
    val badgeName: String,
    val badgeImg: String,
    val hashTags: List<String>,
) : Parcelable
