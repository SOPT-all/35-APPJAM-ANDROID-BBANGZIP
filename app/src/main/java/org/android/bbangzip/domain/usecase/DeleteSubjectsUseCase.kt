package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.repository.remote.SubjectRepository

class DeleteSubjectsUseCase(
    private val subjectRepository: SubjectRepository
) {
    suspend operator fun invoke(): Result<Unit> =
        subjectRepository.deleteSubjects()
}