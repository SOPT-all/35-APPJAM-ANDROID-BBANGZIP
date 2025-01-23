package org.android.bbangzip.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.card.ToDoCardModel

@Parcelize
data class SubjectDetailInfo(
    val examDate: String,
    val examDday: Int,
    val motivationMessage: String,
    val todoList: List<ToDoCardModel>,
) : Parcelable
