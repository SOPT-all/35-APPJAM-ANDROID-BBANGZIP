package org.android.bbangzip.domain.usecase

import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.domain.repository.remote.PieceRepository

class PostUnCompleteCardIdUseCase(
    private val pieceRepository: PieceRepository
) {
    suspend operator fun invoke(
        pieceId: Int,
        requestMarkDoneDto: RequestMarkDoneDto
    ): Result<Unit> =
        pieceRepository.postUnCompleteCardId(
            pieceId = pieceId,
            requestMarkDoneDto = requestMarkDoneDto
        )
}