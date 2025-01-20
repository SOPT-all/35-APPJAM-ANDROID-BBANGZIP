package org.android.bbangzip.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.bbangzip.domain.model.ToDoCardEntity
import org.android.bbangzip.domain.model.ToDoInfoEntity

@Serializable
data class ResponseTodoDto(
    @SerialName("todoCount")
    val todoCount: Int,
    @SerialName("todoList")
    val todoList: List<TodoCardInfo>,
) {
    fun toTodoCardInfoEntity() =
        ToDoInfoEntity(
            todoList =
                todoList.map { todoItem ->
                    todoItem.toTodoCardEntity()
                },
        )
}

@Serializable
data class TodoCardInfo(
    @SerialName("deadline")
    val deadline: String,
    @SerialName("examName")
    val examName: String,
    @SerialName("finishPage")
    val finishPage: Int,
    @SerialName("pieceId")
    val pieceId: Int,
    @SerialName("remainingDays")
    val remainingDays: Int,
    @SerialName("startPage")
    val startPage: Int,
    @SerialName("studyContents")
    val studyContents: String,
    @SerialName("subjectName")
    val subjectName: String,
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
        )
}
