package org.android.bbangzip.domain.model

import org.android.bbangzip.data.dto.request.RequestPieceIdDto

data class PieceIdEntity(
    val piece: List<Int>
) {
    fun toPieceIdDto() = RequestPieceIdDto(
        pieceIds = piece
    )
}
