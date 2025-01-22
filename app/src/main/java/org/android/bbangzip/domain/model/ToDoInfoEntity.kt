package org.android.bbangzip.domain.model

data class ToDoInfoEntity(
    val todoList: List<ToDoCardEntity> = listOf(),
    val pendingCount: Int = 0,
    val remainingStudyCount: Int = 0,
    val completeCount: Int = 0,
)
