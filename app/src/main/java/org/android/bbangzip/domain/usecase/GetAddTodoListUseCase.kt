package org.android.bbangzip.domain.usecase

import org.android.bbangzip.domain.model.ToDoInfoEntity
import org.android.bbangzip.domain.repository.remote.PieceRepository

class GetAddTodoListUseCase(
    private val pieceRepository: PieceRepository
) {
    suspend operator fun invoke(
        year: Int,
        semester: String,
        sortOption: String
    ): Result<ToDoInfoEntity> =
        pieceRepository.getAddTodoList(
            year = year,
            semester = semester,
            sortOption = sortOption
        )
}