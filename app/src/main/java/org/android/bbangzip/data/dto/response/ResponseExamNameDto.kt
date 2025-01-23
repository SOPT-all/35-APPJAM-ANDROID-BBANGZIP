package org.android.bbangzip.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.SubjectDetailInfoEntity
import org.android.bbangzip.domain.model.ToDoCardEntity

@Serializable
data class ResponseExamNameDto(
    @SerialName("examDate")
    val examDate: String,
    @SerialName("examDday")
    val examDday: Int,
    @SerialName("motivationMessage")
    val motivationMessage: String,
    @SerialName("studyList")
    val studyList: List<StudyInfo>,
) {
    fun toSubjectDetailInfoEntity() =
        SubjectDetailInfoEntity(
            examDate = examDate,
            examDday = examDday,
            motivationMessage = motivationMessage,
            todoList = studyList.map { it.toToDoCardEntity() },
        )
}

@Serializable
data class StudyInfo(
    @SerialName("deadline")
    val deadline: String,
    @SerialName("finishPage")
    val finishPage: Int,
    @SerialName("isFinished")
    val isFinished: Boolean,
    @SerialName("pieceId")
    val pieceId: Int,
    @SerialName("remainingDays")
    val remainingDays: Int,
    @SerialName("startPage")
    val startPage: Int,
    @SerialName("studyContents")
    val studyContents: String,
) {
    fun toToDoCardEntity() =
        ToDoCardEntity(
            deadline = deadline,
            finishPage = finishPage,
            pieceId = pieceId,
            remainingDays = remainingDays,
            startPage = startPage,
            studyContents = studyContents,
            isFinished = isFinished,
        )
}
