package org.android.bbangzip.data.service

import org.android.bbangzip.data.dto.response.ResponseDummyDto
import org.android.bbangzip.data.util.constant.ApiConstants.DUMMY
import org.android.bbangzip.data.util.constant.ApiConstants.VERSION
import org.android.bbangzip.data.util.base.BaseResponse
import retrofit2.http.GET

interface DummyService {
    @GET("$VERSION/$DUMMY")
    suspend fun getDummy(): BaseResponse<ResponseDummyDto>
}