package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.request.RequestAddStudyDto
import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.data.dto.response.ResponseGetBadgeDto
import org.android.bbangzip.data.service.StudyService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class StudyRemoteDataSource @Inject constructor(
    private val studyService: StudyService
) {
    suspend fun postStudy(requestAddStudyDto: RequestAddStudyDto): BaseResponse<ResponseGetBadgeDto> = studyService.postStudy(requestAddStudyDto = requestAddStudyDto)

    suspend fun deleteStudyPieces(requestPieceIdDto: RequestPieceIdDto): BaseResponse<String> = studyService.deleteStudyPieces(requestPieceIdDto = requestPieceIdDto)
}