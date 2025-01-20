package org.android.bbangzip.domain.usecase

import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.domain.repository.remote.PieceRepository

class PostAddTodoItemListUseCase(
    private val pieceRepository: PieceRepository
) {
    suspend operator fun invoke(
        requestPieceIdDto: RequestPieceIdDto
    ): Result<Unit> =
        pieceRepository.postAddTodoItemList(
            requestPieceIdDto= requestPieceIdDto
        )
}