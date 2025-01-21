package org.android.bbangzip.data.service

import org.android.bbangzip.data.dto.request.RequestOnboardingDto
import org.android.bbangzip.data.dto.response.ResponseReissueDto
import org.android.bbangzip.data.dto.response.ResponseUserDto
import org.android.bbangzip.data.util.base.BaseResponse
import org.android.bbangzip.data.util.constant.ApiConstants.API
import org.android.bbangzip.data.util.constant.ApiConstants.AUTH
import org.android.bbangzip.data.util.constant.ApiConstants.CODE
import org.android.bbangzip.data.util.constant.ApiConstants.REISSUE
import org.android.bbangzip.data.util.constant.ApiConstants.SIGN_IN
import org.android.bbangzip.data.util.constant.ApiConstants.SIGN_OUT
import org.android.bbangzip.data.util.constant.ApiConstants.SIGN_UP
import org.android.bbangzip.data.util.constant.ApiConstants.USER
import org.android.bbangzip.data.util.constant.ApiConstants.VERSION
import org.android.bbangzip.data.util.constant.ApiConstants.WITHDRAW
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("$API/$VERSION/$USER/$AUTH/$SIGN_IN")
    suspend fun login(
        @Query(CODE) code: String,
    ): BaseResponse<ResponseUserDto>

    @POST("$API/$VERSION/$USER/$AUTH/$REISSUE")
    suspend fun reissue(): BaseResponse<ResponseReissueDto>

    @DELETE("$API/$VERSION/$USER/$AUTH/$SIGN_OUT")
    suspend fun logout(): BaseResponse<String>

    @DELETE("$API/$VERSION/$USER/$AUTH/$WITHDRAW")
    suspend fun withdraw(): BaseResponse<String>

    @PATCH("$API/$VERSION/$USER/$AUTH/$SIGN_UP")
    suspend fun onboardingComplete(
        @Body requestOnboardingDto: RequestOnboardingDto,
    ): BaseResponse<String>
}
