package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.response.ResponseBadgeNameDto
import org.android.bbangzip.data.service.MyPageService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class BadgesNameRemoteDataSource
@Inject
constructor(
    private val myPageService: MyPageService,
) {
    suspend fun getBadgeDetail(badgeName: String): BaseResponse<ResponseBadgeNameDto?> = myPageService.getBadgeDetail(badgeName = badgeName)
}
