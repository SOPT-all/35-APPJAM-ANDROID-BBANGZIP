package org.android.bbangzip.data.service

import org.android.bbangzip.data.dto.response.ResponseBadgeNameDto
import org.android.bbangzip.data.dto.response.ResponseBadgesDto
import org.android.bbangzip.data.dto.response.ResponseMyPageDto
import org.android.bbangzip.data.util.base.BaseResponse
import org.android.bbangzip.data.util.constant.ApiConstants.API
import org.android.bbangzip.data.util.constant.ApiConstants.MY_PAGE
import org.android.bbangzip.data.util.constant.ApiConstants.VERSION
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageService {
    @GET("/api/v1/mypage/badges")
    suspend fun getBadgeCategoryList(): BaseResponse<ResponseBadgesDto?>

    @GET("/api/v1/mypage/badges/{badgeName}")
    suspend fun getBadgeDetail(
        @Path("badgeName") badgeName: String,
    ): BaseResponse<ResponseBadgeNameDto?>

    @GET("$API/$VERSION/$MY_PAGE")
    suspend fun getMyInfo(): BaseResponse<ResponseMyPageDto>
}
