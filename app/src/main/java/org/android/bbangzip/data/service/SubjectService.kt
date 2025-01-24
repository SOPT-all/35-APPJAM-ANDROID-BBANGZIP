package org.android.bbangzip.data.service

import org.android.bbangzip.data.dto.response.ResponseSubjectFilterDto
import org.android.bbangzip.data.util.base.BaseResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface SubjectService {
    @GET("/api/v1/subjects/filter?year=2025&semester=1학기")
    suspend fun getSubjectInfo(): BaseResponse<ResponseSubjectFilterDto?>

    @POST("/api/v1/subjects")
    suspend fun postSubjectName(): BaseResponse<Unit?>

    @DELETE("/api/v1/subjects")
    suspend fun deleteSubjects(): BaseResponse<Unit?>
}
