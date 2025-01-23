package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.domain.model.SubjectDetailInfoEntity

interface ExamRepository {
    suspend fun getSubjectDetail(
        subjectId: Int,
        examName: String,
    ): Result<SubjectDetailInfoEntity>
}
