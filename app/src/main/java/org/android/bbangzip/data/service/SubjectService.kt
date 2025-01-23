package org.android.bbangzip.data.service

import org.android.bbangzip.data.dto.response.ResponseSubjectFilterDto
import org.android.bbangzip.data.util.base.BaseResponse
import retrofit2.http.GET

interface SubjectService {
    @GET("/api/v1/subjects/filter?year=2025&semester=1학기")
    suspend fun getSubjectInfo(): BaseResponse<ResponseSubjectFilterDto?>
}
