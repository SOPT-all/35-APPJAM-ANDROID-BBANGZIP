package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Semester(
    val year: String,
    val semester: String,
) : Parcelable
