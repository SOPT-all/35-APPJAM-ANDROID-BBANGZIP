package org.android.bbangzip.data.service

import org.android.bbangzip.data.dto.response.ResponseExamNameDto
import org.android.bbangzip.data.util.base.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ExamService {
    @GET("/api/v1/exams/{subjectId}/{examName}")
    suspend fun getSubjectDetail(
        @Path("subjectId") subjectId: Int,
        @Path("examName") examName: String,
    ): BaseResponse<ResponseExamNameDto?>
}