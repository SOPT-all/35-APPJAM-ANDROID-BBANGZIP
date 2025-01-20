package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.response.ResponseTodoDto
import org.android.bbangzip.data.service.PieceService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class TodoRemoteDataSource
    @Inject
    constructor(
        private val pieceService: PieceService,
    ) {
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
