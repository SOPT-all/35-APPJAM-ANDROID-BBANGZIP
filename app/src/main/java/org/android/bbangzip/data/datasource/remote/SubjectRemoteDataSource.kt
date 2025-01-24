package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.request.RequestAddSubjectsDto
import org.android.bbangzip.data.dto.request.RequestDeleteSubjectsDto
import org.android.bbangzip.data.dto.request.RequestSubjectOptions
import org.android.bbangzip.data.dto.response.ResponseSubjectFilterDto
import org.android.bbangzip.data.service.SubjectService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class SubjectRemoteDataSource
    @Inject
    constructor(private val subjectService: SubjectService) {
        suspend fun getSubjectInfo(): BaseResponse<ResponseSubjectFilterDto?> = subjectService.getSubjectInfo()

        suspend fun postSubjectName(requestAddSubjectsDto: RequestAddSubjectsDto): BaseResponse<Unit?> = subjectService.postSubjectName(requestAddSubjectsDto)

        suspend fun deleteSubjects(requestDeleteSubjectsDto: RequestDeleteSubjectsDto): BaseResponse<Unit?> = subjectService.deleteSubjects(requestDeleteSubjectsDto)

        suspend fun putSubjectOptions(
            subjectId: Int,
            options: String,
            requestSubjectOptions: RequestSubjectOptions,
        ): BaseResponse<Unit?> = subjectService.putSubjectOptions(subjectId, options, requestSubjectOptions)
    }
