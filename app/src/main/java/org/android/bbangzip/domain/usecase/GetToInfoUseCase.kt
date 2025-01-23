package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.ToDoInfoEntity
import org.android.bbangzip.domain.repository.remote.PieceRepository

class GetToInfoUseCase(
    private val pieceRepository: PieceRepository,
) {
    suspend operator fun invoke(
        area: String,
        year: Int,
        semester: String,
        sortOption: String,
    ): Result<ToDoInfoEntity> =
        pieceRepository.getTodoInfo(
            area = area,
            year = year,
            semester = semester,
            sortOption = sortOption,
        )
}
