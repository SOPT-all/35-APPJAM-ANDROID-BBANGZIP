package org.android.bbangzip.domain.model

data class SubjectInfoEntity(
    val semester: String,
    val subjectList: List<SubjectCardInfoEntity>,
    val year: Int,
)

data class SubjectCardInfoEntity(
    val studyList: List<SubjectCardDetailTodoInfoEntity?>,
    val subjectId: Int,
    val subjectName: String,
)

data class SubjectCardDetailTodoInfoEntity(
    val examDday: Int,
    val examName: String?,
    val pendingCount: Int,
    val remainingCount: Int,
)
