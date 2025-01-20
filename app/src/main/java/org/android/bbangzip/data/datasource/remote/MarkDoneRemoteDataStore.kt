package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.data.dto.response.ResponseMarkDoneDto
import org.android.bbangzip.data.service.PieceService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class MarkDoneRemoteDataStore @Inject
constructor(
    private val pieceService: PieceService,
) {
    suspend fun postCompleteCardId(
        pieceId:Int , requestMarkDoneDto: RequestMarkDoneDto
    ): BaseResponse<ResponseMarkDoneDto?> = pieceService.postCompleteCardId(pieceId = pieceId, requestMarkDoneDto = requestMarkDoneDto)
}
