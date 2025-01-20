package org.android.bbangzip.domain.model

data class ToDoCardEntity(
    val pieceId: Int = 0,
    val subjectName: String = "",
    val examName: String = "",
    val studyContents: String,
    val startPage: Int = 0,
    val finishPage: Int= 0,
    val deadline: String = "",
    val remainingDays: Int = 0,
    val isFinished: Boolean = false,
)
