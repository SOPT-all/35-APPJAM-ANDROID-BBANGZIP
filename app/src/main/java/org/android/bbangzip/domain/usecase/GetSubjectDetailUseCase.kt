package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.BadgeDetailEntity
import org.android.bbangzip.domain.model.SubjectDetailInfoEntity
import org.android.bbangzip.domain.repository.remote.ExamRepository
import org.android.bbangzip.domain.repository.remote.MyPageRepository

class GetSubjectDetailUseCase(
    private val examRepository: ExamRepository,
) {
    suspend operator fun invoke(subjectId: Int, examName: String): Result<SubjectDetailInfoEntity> =
        examRepository.getSubjectDetail(subjectId = subjectId, examName = examName)
}