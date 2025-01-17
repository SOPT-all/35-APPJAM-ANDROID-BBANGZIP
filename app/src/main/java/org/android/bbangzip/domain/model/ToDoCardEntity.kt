package org.android.bbangzip.domain.model

data class ToDoCardEntity(
    val pieceId: Int,
    val subjectName: String = "",
    val examName: String = "",
    val studyContents: String,
    val startPage: Int,
    val finishPage: Int,
    val deadline: String,
    val remainingDays: Int,
    val isFinished: Boolean = false,
)
