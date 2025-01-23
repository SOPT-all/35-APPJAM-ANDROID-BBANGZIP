package org.android.bbangzip.presentation.model.card

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState

@Parcelize
data class SubjectCardModel(
    val subjectId: Int,
    val subjectName: String,
    val examName: String,
    val examRemainingDays: Int,
    val pendingCount: Int,
    val inProgressCount: Int,
    val state: BbangZipCardState = BbangZipCardState.DEFAULT,
) : Parcelable
