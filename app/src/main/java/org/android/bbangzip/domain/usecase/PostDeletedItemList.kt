package org.android.bbangzip.domain.usecase

import org.android.bbangzip.data.dto.request.RequestHideDto
import org.android.bbangzip.domain.repository.remote.PieceRepository

class PostDeletedItemList(
    private val pieceRepository: PieceRepository
) {
    suspend operator fun invoke(
       requestHideDto: RequestHideDto
    ): Result<Unit> =
        pieceRepository.postDeletedItemList(
           requestHideDto= requestHideDto
        )
}