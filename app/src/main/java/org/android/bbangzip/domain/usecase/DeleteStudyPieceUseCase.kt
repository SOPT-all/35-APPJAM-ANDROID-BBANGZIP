package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.PieceIdEntity
import org.android.bbangzip.domain.repository.remote.StudyRepository

class DeleteStudyPieceUseCase(
    private val studyRepository: StudyRepository
) {
    suspend operator fun invoke(pieceIdEntity: PieceIdEntity): Result<String> =
        studyRepository.deleteStudyPiece(pieceIdEntity = pieceIdEntity)
}