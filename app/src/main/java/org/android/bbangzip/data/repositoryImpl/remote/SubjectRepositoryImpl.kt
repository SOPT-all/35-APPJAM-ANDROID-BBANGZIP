package org.android.bbangzip.data.repositoryImpl.remote

import org.android.bbangzip.data.datasource.remote.ExamRemoteDataSource
import org.android.bbangzip.data.datasource.remote.SubjectRemoteDataSource
import org.android.bbangzip.domain.model.SubjectInfoEntity
import org.android.bbangzip.domain.repository.remote.ExamRepository
import org.android.bbangzip.domain.repository.remote.SubjectRepository
import javax.inject.Inject

class SubjectRepositoryImpl
@Inject
constructor(
    private val subjectRemoteDataSource: SubjectRemoteDataSource,
) : SubjectRepository {
    override suspend fun getSubjectInfo(): Result<SubjectInfoEntity> =
        runCatching {
            val response = subjectRemoteDataSource.getSubjectInfo()

            val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")

            responseData.toSubjectInfoEntity()
        }

}