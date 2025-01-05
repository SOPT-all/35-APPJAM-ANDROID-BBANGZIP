package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.response.ResponseDummyDto
import org.android.bbangzip.data.util.base.BaseResponse

interface DummyRemoteDataSource {
    suspend fun getDummyData(): BaseResponse<ResponseDummyDto>
}
