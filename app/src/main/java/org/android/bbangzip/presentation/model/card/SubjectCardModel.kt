package org.android.bbangzip.presentation.model.card

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubjectCardModel(
    val subjectId: Int,
    val subjectName: String,
    val examName: String,
    val examRemainingDays: Int,
    val pendingCount: Int,
    val inProgressCount: Int,
) :  Parcelable
