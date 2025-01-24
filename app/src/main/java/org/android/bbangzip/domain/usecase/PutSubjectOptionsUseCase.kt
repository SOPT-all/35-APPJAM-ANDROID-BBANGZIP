package org.android.bbangzip.domain.usecase

import org.android.bbangzip.data.dto.request.RequestSubjectOptions
import org.android.bbangzip.domain.repository.remote.SubjectRepository

class PutSubjectOptionsUseCase(
    private val subjectRepository: SubjectRepository,
) {
    suspend operator fun invoke(
        subjectId: Int,
        options: String,
        requestSubjectOptions: RequestSubjectOptions,
    ): Result<Unit> =
        subjectRepository.putSubjectOptions(
            subjectId = subjectId,
            options = options,
            requestSubjectOptions = requestSubjectOptions,
        )
}
