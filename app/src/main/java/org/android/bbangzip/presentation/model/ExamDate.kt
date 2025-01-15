package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExamDate(
    val year: String,
    val month: String,
    val day: String,
) : Parcelable
