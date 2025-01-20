package org.android.bbangzip.domain.usecase

import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.domain.model.BadgeCardListEntity
import org.android.bbangzip.domain.repository.remote.PieceRepository

class PostCompleteCardIdUseCase(
    private val pieceRepository: PieceRepository,
) {
    suspend operator fun invoke(
        pieceId: Int,
        requestMarkDoneDto: RequestMarkDoneDto,
    ): Result<BadgeCardListEntity> =
        pieceRepository.postCompleteCardID(
            pieceId = pieceId,
            requestMarkDoneDto = requestMarkDoneDto,
        )
}
