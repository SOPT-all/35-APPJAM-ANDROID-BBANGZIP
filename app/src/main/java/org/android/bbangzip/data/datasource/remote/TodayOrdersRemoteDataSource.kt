package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.response.ResponseTodayOrdersDto
import org.android.bbangzip.data.service.PieceService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class TodayOrdersRemoteDataSource @Inject
constructor(
    private val pieceService: PieceService,
) {
    suspend fun getTodoInfo(
        area: String,
        year: Int,
        semester: String,
        sortOption: String
    ): BaseResponse<ResponseTodayOrdersDto> = pieceService.getTodoInfo(
        area = area,
        year = year,
        semester = semester,
        sortOption = sortOption
    )
}
