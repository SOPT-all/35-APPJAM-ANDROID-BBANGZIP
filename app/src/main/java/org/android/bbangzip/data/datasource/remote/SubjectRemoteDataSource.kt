package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.response.ResponseSubjectFilterDto
import org.android.bbangzip.data.service.SubjectService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class SubjectRemoteDataSource @Inject constructor(private val subjectService: SubjectService) {
    suspend fun getSubjectInfo():BaseResponse<ResponseSubjectFilterDto?> = subjectService.getSubjectInfo()
}