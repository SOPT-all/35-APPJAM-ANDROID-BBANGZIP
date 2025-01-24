package org.android.bbangzip.data.repositoryImpl.remote

import org.android.bbangzip.data.datasource.remote.SubjectRemoteDataSource
import org.android.bbangzip.data.dto.request.RequestAddSubjectsDto
import org.android.bbangzip.data.dto.request.RequestDeleteSubjectsDto
import org.android.bbangzip.data.dto.request.RequestSubjectOptions
import org.android.bbangzip.domain.model.SubjectInfoEntity
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

    override suspend fun postSubjectName(requestAddSubjectsDto: RequestAddSubjectsDto): Result<Unit> =
        runCatching {
            val response = subjectRemoteDataSource.postSubjectName(requestAddSubjectsDto = requestAddSubjectsDto)

            response.code ?: throw IllegalStateException(response.message ?: "Null Error")
        }

    override suspend fun deleteSubjects(requestDeleteSubjectsDto: RequestDeleteSubjectsDto): Result<Unit> =
        runCatching {
            val response = subjectRemoteDataSource.deleteSubjects(requestDeleteSubjectsDto = requestDeleteSubjectsDto)

            response.code ?: throw IllegalStateException(response.message ?: "Null Error")
        }

    override suspend fun putSubjectOptions(subjectId: Int, options: String, requestSubjectOptions: RequestSubjectOptions): Result<Unit> =
        runCatching {
            val response = subjectRemoteDataSource.putSubjectOptions(subjectId, options, requestSubjectOptions)

            response.code ?: throw IllegalStateException(response.message ?: "Null Error")

        }
}
