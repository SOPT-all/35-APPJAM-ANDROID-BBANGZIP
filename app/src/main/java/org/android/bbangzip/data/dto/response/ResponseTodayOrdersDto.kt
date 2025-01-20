package org.android.bbangzip.data.dto.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.ToDoCardEntity
import org.android.bbangzip.domain.model.ToDoInfoEntity

@Serializable
data class ResponseTodayOrdersDto(
    @SerialName("completeCount")
    val completeCount: Int?,
    @SerialName("todoPiecesList")
    val todayList: List<TodoInfo>,
    @SerialName("pendingCount")
    val pendingCount: Int,
    @SerialName("todayCount")
    val todayCount: Int?,
) {
    fun toTodoInfoEntity() =
        ToDoInfoEntity(
            todoList = todayList.map { todoItem ->
                todoItem.toTodoCardEntity()
            },
            pendingCount = pendingCount,
            remainingStudyCount = todayCount?:0,
            completeCount = completeCount?:0
        )
}


@Serializable
data class TodoInfo(
    @SerialName("deadline")
    val deadline: String,
    @SerialName("examName")
    val examName: String,
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
    @SerialName("subjectName")
    val subjectName: String
) {
    fun toTodoCardEntity() =
        ToDoCardEntity(
            pieceId = pieceId,
            subjectName = subjectName,
            examName = examName,
            studyContents = studyContents,
            startPage = startPage,
            finishPage = finishPage,
            deadline = deadline,
            remainingDays = remainingDays,
            isFinished = isFinished
        )
}