package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.domain.model.BadgeCardListEntity
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

    suspend fun postCompleteCardID(
        pieceId:Int,
        requestMarkDoneDto: RequestMarkDoneDto
    ):Result<BadgeCardListEntity>

    suspend fun postUnCompleteCardId(
        pieceId:Int,
        requestMarkDoneDto: RequestMarkDoneDto
    ):Result<Unit>
}

