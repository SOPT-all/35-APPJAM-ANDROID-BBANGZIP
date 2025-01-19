package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnboardingInfo(
    val userName: String?,
    val year: Int,
    val semester: String?,
    val subjectName: String?,
) : Parcelable
