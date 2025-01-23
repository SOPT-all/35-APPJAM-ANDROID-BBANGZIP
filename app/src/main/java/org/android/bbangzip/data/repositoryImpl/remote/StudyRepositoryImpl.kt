package org.android.bbangzip.data.repositoryImpl.remote

import org.android.bbangzip.data.datasource.remote.StudyRemoteDataSource
import org.android.bbangzip.domain.model.AddStudyEntity
import org.android.bbangzip.domain.model.GetBadgeEntity
import org.android.bbangzip.domain.model.PieceIdEntity
import org.android.bbangzip.domain.repository.remote.StudyRepository
import javax.inject.Inject

class StudyRepositoryImpl @Inject constructor(
    private val studyRemoteDataSource: StudyRemoteDataSource
) : StudyRepository {
    override suspend fun postAddStudy(addStudyEntity: AddStudyEntity): Result<GetBadgeEntity> =
        runCatching {
            val response = studyRemoteDataSource.postStudy(requestAddStudyDto = addStudyEntity.toAddStudyDto())
            val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")
            responseData.toGetBadgeEntity()
        }

    override suspend fun deleteStudyPiece(pieceIdEntity: PieceIdEntity): Result<String> =
        runCatching {
            val response = studyRemoteDataSource.deleteStudyPieces(requestPieceIdDto = pieceIdEntity.toPieceIdDto())
            val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")
            responseData
        }
}