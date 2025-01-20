package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.request.RequestHideDto
import org.android.bbangzip.data.service.PieceService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class HideRemoteDataSource @Inject
constructor(
    private val pieceService: PieceService,
) {
    suspend fun postDeletedItemList(
        requestHideDto: RequestHideDto
    ): BaseResponse<Unit> = pieceService.postDeletedItemList(requestHideDto = requestHideDto)
}