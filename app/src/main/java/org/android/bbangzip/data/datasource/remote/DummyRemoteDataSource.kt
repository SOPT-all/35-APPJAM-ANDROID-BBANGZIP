package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.response.ResponseDummyDto
import org.android.bbangzip.data.service.DummyService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class DummyRemoteDataSource
    @Inject
    constructor(
        private val dummyService: DummyService,
    ) {
        suspend fun getDummyData(): BaseResponse<ResponseDummyDto?> = dummyService.getDummy()
    }
