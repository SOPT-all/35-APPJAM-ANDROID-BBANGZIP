package org.android.bbangzip.data.repositoryImpl.remote

import org.android.bbangzip.data.datasource.remote.ExamRemoteDataSource
import org.android.bbangzip.domain.model.SubjectDetailInfoEntity
import org.android.bbangzip.domain.repository.remote.ExamRepository
import javax.inject.Inject

class ExamRepositoryImpl
@Inject
constructor(
    private val examRemoteDataSource: ExamRemoteDataSource,
) : ExamRepository {
    override suspend fun getSubjectDetail(
        subjectId: Int,
        examName: String,
    ): Result<SubjectDetailInfoEntity> =
        runCatching {
            val response = examRemoteDataSource.getSubjectDetail(subjectId = subjectId, examName = examName)

            val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")

            responseData.toSubjectDetailInfoEntity()
        }
}
