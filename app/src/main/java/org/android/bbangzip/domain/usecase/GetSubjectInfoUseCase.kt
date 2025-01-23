package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.SubjectInfoEntity
import org.android.bbangzip.domain.repository.remote.SubjectRepository

class GetSubjectInfoUseCase(
    private val subjectRepository: SubjectRepository,
) {
    suspend operator fun invoke(): Result<SubjectInfoEntity> =
        subjectRepository.getSubjectInfo()
}
