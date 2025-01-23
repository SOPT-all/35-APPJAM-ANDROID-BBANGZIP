package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.data.dto.response.ResponseMarkDoneDto
import org.android.bbangzip.data.dto.response.ResponseTodayOrdersDto
import org.android.bbangzip.data.dto.response.ResponseTodoDto
import org.android.bbangzip.data.service.PieceService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class PieceRemoteDataSource
    @Inject
    constructor(
        private val pieceService: PieceService,
    ) {
        suspend fun postAddTodoItemList(
            requestPieceIdDto: RequestPieceIdDto,
        ): BaseResponse<Unit?> = pieceService.postAddTodoItemList(requestPieceIdDto = requestPieceIdDto)

        suspend fun postDeletedItemList(
            requestPieceIdDto: RequestPieceIdDto,
        ): BaseResponse<Unit?> = pieceService.postDeletedItemList(requestPieceIdDto = requestPieceIdDto)

        suspend fun postCompleteCardId(
            pieceId: Int,
            requestMarkDoneDto: RequestMarkDoneDto,
        ): BaseResponse<ResponseMarkDoneDto?> = pieceService.postCompleteCardId(pieceId = pieceId, requestMarkDoneDto = requestMarkDoneDto)

        suspend fun postUnCompleteCardId(
            pieceId: Int,
            requestMarkDoneDto: RequestMarkDoneDto,
        ): BaseResponse<Unit?> = pieceService.postUnCompleteCardId(pieceId = pieceId, requestMarkDoneDto = requestMarkDoneDto)

        suspend fun getTodoInfo(
            area: String,
            year: Int,
            semester: String,
            sortOption: String,
        ): BaseResponse<ResponseTodayOrdersDto?> =
            pieceService.getTodoInfo(
                area = area,
                year = year,
                semester = semester,
                sortOption = sortOption,
            )

        suspend fun getAddTodolist(
            year: Int,
            semester: String,
            sortOption: String,
        ): BaseResponse<ResponseTodoDto?> =
            pieceService.getAddTodoList(
                year = year,
                semester = semester,
                sortOption = sortOption,
            )
    }
