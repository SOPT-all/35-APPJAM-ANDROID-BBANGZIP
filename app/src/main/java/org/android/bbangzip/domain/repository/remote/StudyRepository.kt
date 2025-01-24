package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.domain.model.AddStudyEntity
import org.android.bbangzip.domain.model.GetBadgeEntity
import org.android.bbangzip.domain.model.PieceIdEntity

interface StudyRepository {
    suspend fun postAddStudy(addStudyEntity: AddStudyEntity): Result<GetBadgeEntity>

    suspend fun deleteStudyPiece(pieceIdEntity: PieceIdEntity): Result<String>
}
