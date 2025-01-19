package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.domain.model.ToDoInfoEntity

interface PieceRepository {
    suspend fun getTodoInfo(
        area: String,
        year: Int,
        semester: String,
        sortOption: String
    ): Result<ToDoInfoEntity>
}

