package org.android.bbangzip.data.datasource.remote

import org.android.bbangzip.data.dto.response.ResponseMyPageDto
import org.android.bbangzip.data.service.MyPageService
import org.android.bbangzip.data.util.base.BaseResponse
import javax.inject.Inject

class MyPageRemoteDataSource @Inject constructor(
    private val myPageService: MyPageService
){
    suspend fun getMyPageInfo(): BaseResponse<ResponseMyPageDto> = myPageService.getMyInfo()
}