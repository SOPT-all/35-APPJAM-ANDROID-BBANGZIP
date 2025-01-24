package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.AddStudyEntity
import org.android.bbangzip.domain.model.GetBadgeEntity
import org.android.bbangzip.domain.repository.remote.StudyRepository

class PostAddStudyUseCase(
    private val studyRepository: StudyRepository,
) {
    suspend operator fun invoke(addStudyEntity: AddStudyEntity): Result<GetBadgeEntity> =
        studyRepository.postAddStudy(addStudyEntity = addStudyEntity)
}
