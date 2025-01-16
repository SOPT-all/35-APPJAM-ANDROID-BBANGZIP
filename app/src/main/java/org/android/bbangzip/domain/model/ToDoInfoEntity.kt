package org.android.bbangzip.domain.model

data class ToDoInfoEntity(
    val todoList : List<ToDoCardEntity>,
    val pendingCount: Int,
    val remainingStudyCount : Int,
    val completeCount : Int,
)
