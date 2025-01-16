package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.type.SemesterType

@Parcelize
data class Semester(
    val year: String,
    val semester: SemesterType,
) : Parcelable
