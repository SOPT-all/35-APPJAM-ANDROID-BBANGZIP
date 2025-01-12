package org.android.bbangzip.presentation.component.card.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface BbangZipCardData {
    @Parcelize
    data class TodoCardData(
        val pieceId: String,
        val subjectName: String,
        val examName: String,
        val studyContents: String,
        val startPage: Int,
        val finishPage: Int,
        val deadline: String,
        val remainingDays: Int,
    ) : BbangZipCardData, Parcelable

    @Parcelize
    data class SubjectCardData(
        val subjectId: Int,
        val subjectName: String,
        val examName: String,
        val examRemainingDays: Int,
        val pendingCount: Int,
        val inProgressCount: Int
    ) : BbangZipCardData, Parcelable
}