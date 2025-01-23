package org.android.bbangzip.presentation.model.card

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState

@Parcelize
data class ToDoCardModel(
    val pieceId: Int,
    val subjectName: String,
    val examName: String,
    val studyContents: String,
    val startPage: Int,
    val finishPage: Int,
    val deadline: String,
    val remainingDays: Int,
    val cardState: BbangZipCardState,
) : Parcelable
