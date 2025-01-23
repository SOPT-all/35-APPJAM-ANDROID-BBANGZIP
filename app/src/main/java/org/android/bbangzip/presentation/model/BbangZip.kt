package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BbangZip(
    val level: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val isLocked: Boolean,
) : Parcelable
