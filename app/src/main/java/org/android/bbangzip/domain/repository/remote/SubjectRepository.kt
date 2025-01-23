package org.android.bbangzip.domain.repository.remote

import org.android.bbangzip.domain.model.SubjectInfoEntity

interface SubjectRepository {
    suspend fun getSubjectInfo(): Result<SubjectInfoEntity>
}
