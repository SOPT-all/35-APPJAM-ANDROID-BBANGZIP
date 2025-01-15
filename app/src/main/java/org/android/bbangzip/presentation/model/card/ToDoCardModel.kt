package org.android.bbangzip.presentation.model.card

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToDoCardModel(
    val pieceId: String,
    val subjectName: String,
    val examName: String,
    val studyContents: String,
    val startPage: Int,
    val finishPage: Int,
    val deadline: String,
    val remainingDays: Int,
) : Parcelable
