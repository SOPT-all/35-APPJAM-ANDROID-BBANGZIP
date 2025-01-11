package org.android.bbangzip.presentation.type

sealed interface StudyCardData {
    data class TodoCardData(
        val pieceId: String,
        val subjectName: String,
        val examName: String,
        val studyContents: String,
        val startPage: Int,
        val finishPage: Int,
        val deadline: String,
        val remainingDays: Int,
        val isFinished: Boolean,
        val todoItemType: String
    ) : StudyCardData

    data class SubjectCardData(
        val subjectId: Int,
        val subjectName: String,
        val examName: String,
        val examRemainingDays: Int,
        val pendingCount: Int,
        val inProgressCount: Int
    ) : StudyCardData
}