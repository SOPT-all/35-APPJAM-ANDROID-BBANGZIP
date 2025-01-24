package org.android.bbangzip.domain.usecase

import org.android.bbangzip.data.dto.request.RequestDeleteSubjectsDto
import org.android.bbangzip.domain.repository.remote.SubjectRepository

class DeleteSubjectsUseCase(
    private val subjectRepository: SubjectRepository
) {
    suspend operator fun invoke(requestDeleteSubjectsDto: RequestDeleteSubjectsDto): Result<Unit> =
        subjectRepository.deleteSubjects(requestDeleteSubjectsDto = requestDeleteSubjectsDto)
}