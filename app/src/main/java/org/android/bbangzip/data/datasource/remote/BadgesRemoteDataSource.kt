package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.response.ResponseBadgesDto
import org.android.bbangzip.data.service.MyPageService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class BadgesRemoteDataSource
    @Inject
    constructor(
        private val myPageService: MyPageService,
    ) {
        suspend fun getBadgeCategoryList(): BaseResponse<ResponseBadgesDto?> = myPageService.getBadgeCategoryList()
    }
