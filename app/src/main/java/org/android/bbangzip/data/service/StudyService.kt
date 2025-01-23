package org.android.bbangzip.data.service

import org.android.bbangzip.data.dto.request.RequestAddStudyDto
import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.data.dto.response.ResponseGetBadgeDto
import org.android.bbangzip.data.util.base.BaseResponse
import org.android.bbangzip.data.util.constant.ApiConstants.API
import org.android.bbangzip.data.util.constant.ApiConstants.PIECES
import org.android.bbangzip.data.util.constant.ApiConstants.STUDIES
import org.android.bbangzip.data.util.constant.ApiConstants.VERSION
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface StudyService {
    @POST("$API/$VERSION/$STUDIES")
    suspend fun postStudy(
        @Body requestAddStudyDto: RequestAddStudyDto
    ): BaseResponse<ResponseGetBadgeDto>

    @DELETE("$API/$VERSION/$STUDIES/$PIECES")
    suspend fun deleteStudyPieces(
        @Body requestPieceIdDto: RequestPieceIdDto
    ): BaseResponse<String>
}