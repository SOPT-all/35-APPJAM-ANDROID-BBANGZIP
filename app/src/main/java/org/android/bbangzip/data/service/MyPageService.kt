package org.android.bbangzip.data.service

import org.android.bbangzip.data.dto.response.ResponseMyPageDto
import org.android.bbangzip.data.util.base.BaseResponse
import org.android.bbangzip.data.util.constant.ApiConstants.API
import org.android.bbangzip.data.util.constant.ApiConstants.MY_PAGE
import org.android.bbangzip.data.util.constant.ApiConstants.VERSION
import retrofit2.http.GET

interface MyPageService {
    @GET("$API/$VERSION/$MY_PAGE")
    suspend fun getMyInfo(): BaseResponse<ResponseMyPageDto>
}