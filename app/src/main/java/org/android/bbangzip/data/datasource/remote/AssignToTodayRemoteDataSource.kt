package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.data.service.PieceService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class AssignToTodayRemoteDataSource @Inject
constructor(
    private val pieceService: PieceService,
) {
    suspend fun postDeletedItemList(
        requestPieceIdDto: RequestPieceIdDto
    ): BaseResponse<Unit?>? = pieceService.postAddTodoItemList(requestPieceIdDto = requestPieceIdDto)
}