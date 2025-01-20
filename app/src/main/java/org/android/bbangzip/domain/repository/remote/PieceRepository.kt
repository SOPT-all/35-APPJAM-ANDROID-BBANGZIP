package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.domain.model.ToDoInfoEntity

interface PieceRepository {
    suspend fun getTodoInfo(
        area: String,
        year: Int,
        semester: String,
        sortOption: String
    ): Result<ToDoInfoEntity>

    suspend fun  getAddTodoList(
        year: Int,
        semester: String,
        sortOption: String
    ): Result<ToDoInfoEntity>

    suspend fun postDeletedItemList(
        requestPieceIdDto: RequestPieceIdDto
    ) : Result<Unit>

    suspend fun postAddTodoItemList(
        requestPieceIdDto: RequestPieceIdDto
    ) : Result<Unit>
}

