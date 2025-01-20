package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.data.service.PieceService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class MarkUnDoneRemoteDataStore
    @Inject
    constructor(
        private val pieceService: PieceService,
    ) {
        suspend fun postUnCompleteCardId(
            pieceId: Int,
            requestMarkDoneDto: RequestMarkDoneDto,
        ): BaseResponse<Unit?> = pieceService.postUnCompleteCardId(pieceId = pieceId, requestMarkDoneDto = requestMarkDoneDto)
    }
