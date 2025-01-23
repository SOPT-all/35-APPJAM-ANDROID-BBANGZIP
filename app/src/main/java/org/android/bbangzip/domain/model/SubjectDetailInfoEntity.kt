package org.android.bbangzip.domain.model

data class SubjectDetailInfoEntity(
    val examDate: String,
    val examDday: Int,
    val motivationMessage: String,
    val todoList: List<ToDoCardEntity>,
)
