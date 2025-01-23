package org.android.bbangzip.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.SubjectCardDetailTodoInfoEntity
import org.android.bbangzip.domain.model.SubjectCardInfoEntity
import org.android.bbangzip.domain.model.SubjectInfoEntity

@Serializable
data class ResponseSubjectFilterDto(
    @SerialName("semester")
    val semester: String,
    @SerialName("subjectList")
    val subjectList: List<SubjectListInfo>,
    @SerialName("year")
    val year: Int,
) {
    fun toSubjectInfoEntity() =
        SubjectInfoEntity(
            semester = semester,
            subjectList = subjectList.map { it.toSubjectCardInfoEntity() },
            year = year,
        )
}

@Serializable
data class SubjectListInfo(
    @SerialName("studyList")
    val studyList: List<SubjectStudyInfo>,
    @SerialName("subjectId")
    val subjectId: Int,
    @SerialName("subjectName")
    val subjectName: String,
) {
    fun toSubjectCardInfoEntity() =
        SubjectCardInfoEntity(
            subjectId = subjectId,
            subjectName = subjectName,
            studyList =
                studyList.filter { data ->
                    data.examDday < 0
                }.map {
                    it.toSubjectCardDetailTodoInfoEntity()
                },
        )
}

@Serializable
data class SubjectStudyInfo(
    @SerialName("examDDay")
    val examDday: Int,
    @SerialName("examName")
    val examName: String,
    @SerialName("pendingCount")
    val pendingCount: Int,
    @SerialName("remainingCount")
    val remainingCount: Int,
) {
    fun toSubjectCardDetailTodoInfoEntity() =
        SubjectCardDetailTodoInfoEntity(
            examDday = examDday,
            examName = examName,
            pendingCount = pendingCount,
            remainingCount = remainingCount,
        )
}
