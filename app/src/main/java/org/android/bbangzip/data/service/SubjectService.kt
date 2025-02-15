package org.android.bbangzip.data.service

import org.android.bbangzip.data.dto.request.RequestAddSubjectsDto
import org.android.bbangzip.data.dto.request.RequestDeleteSubjectsDto
import org.android.bbangzip.data.dto.request.RequestSubjectOptions
import org.android.bbangzip.data.dto.response.ResponseSubjectFilterDto
import org.android.bbangzip.data.util.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path

interface SubjectService {
    @GET("/api/v1/subjects/filter?year=2025&semester=1학기")
    suspend fun getSubjectInfo(): BaseResponse<ResponseSubjectFilterDto?>

    @POST("/api/v1/subjects")
    suspend fun postSubjectName(
        @Body requestAddSubjectsDto: RequestAddSubjectsDto,
    ): BaseResponse<Unit?>

    @HTTP(method = "DELETE", path = "/api/v1/subjects", hasBody = true)
    suspend fun deleteSubjects(
        @Body requestDeleteSubjectsDto: RequestDeleteSubjectsDto,
    ): BaseResponse<Unit?>

    @HTTP(method = "PUT", path = "/api/v1/subjects/{subjectId}/{options}", hasBody = true)
    suspend fun putSubjectOptions(
        @Path("subjectId") subjectId: Int,
        @Path("options") options: String,
        @Body requestSubjectOptions: RequestSubjectOptions,
    ): BaseResponse<Unit?>
}
