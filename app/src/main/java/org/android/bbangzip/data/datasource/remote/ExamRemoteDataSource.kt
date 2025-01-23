package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.response.ResponseExamNameDto
import org.android.bbangzip.data.service.ExamService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class ExamRemoteDataSource
@Inject
constructor(
    private val examService: ExamService,
) {
    suspend fun getSubjectDetail(
        subjectId: Int,
        examName: String,
    ):BaseResponse<ResponseExamNameDto?> = examService.getSubjectDetail(subjectId = subjectId, examName = examName)
}