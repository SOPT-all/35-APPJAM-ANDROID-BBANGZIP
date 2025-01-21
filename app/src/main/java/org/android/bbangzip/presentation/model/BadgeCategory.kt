package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BadgeCategory(
    val name : String,
    val categoryName : String,
    val isLocked : Boolean,
    val imageUrl :String
) : Parcelable
