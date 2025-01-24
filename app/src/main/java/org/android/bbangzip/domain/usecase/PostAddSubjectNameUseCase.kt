package org.android.bbangzip.domain.usecase

import org.android.bbangzip.data.dto.request.RequestAddSubjectsDto
import org.android.bbangzip.domain.repository.remote.SubjectRepository

class PostAddSubjectNameUseCase(
    private val subjectRepository: SubjectRepository,
) {
    suspend operator fun invoke(requestAddSubjectsDto: RequestAddSubjectsDto): Result<Unit> =
        subjectRepository.postSubjectName(requestAddSubjectsDto = requestAddSubjectsDto)
}
