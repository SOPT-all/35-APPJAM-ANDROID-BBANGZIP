package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.request.RequestOnboardingDto
import org.android.bbangzip.data.dto.response.ResponseReissueDto
import org.android.bbangzip.data.dto.response.ResponseUserDto
import org.android.bbangzip.data.service.UserService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class UserRemoteDataSource
    @Inject
    constructor(
        private val userService: UserService,
    ) {
        suspend fun login(code: String): BaseResponse<ResponseUserDto> = userService.login(code = code)

        suspend fun reissue(): BaseResponse<ResponseReissueDto> = userService.reissue()

        suspend fun logout(): BaseResponse<String> = userService.logout()

        suspend fun withDraw(): BaseResponse<String> = userService.withdraw()

        suspend fun onboardingComplete(requestOnboardingDto: RequestOnboardingDto): BaseResponse<String> = userService.onboardingComplete(requestOnboardingDto = requestOnboardingDto)
    }
