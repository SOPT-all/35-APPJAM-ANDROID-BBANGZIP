package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.data.dto.request.RequestAddSubjectsDto
import org.android.bbangzip.data.dto.request.RequestDeleteSubjectsDto
import org.android.bbangzip.data.dto.request.RequestSubjectOptions
import org.android.bbangzip.data.util.base.BaseResponse
import org.android.bbangzip.domain.model.SubjectInfoEntity

interface SubjectRepository {
    suspend fun getSubjectInfo(): Result<SubjectInfoEntity>

    suspend fun postSubjectName(requestAddSubjectsDto: RequestAddSubjectsDto): Result<Unit>

    suspend fun deleteSubjects(requestDeleteSubjectsDto: RequestDeleteSubjectsDto): Result<Unit>

    suspend fun putSubjectOptions(
        subjectId: Int,
        options: String,
        requestSubjectOptions: RequestSubjectOptions
    ): Result<Unit>
}
