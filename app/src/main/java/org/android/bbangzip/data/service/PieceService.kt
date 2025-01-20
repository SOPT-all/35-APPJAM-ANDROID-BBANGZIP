package org.android.bbangzip.data.service

import org.android.bbangzip.data.dto.response.ResponseTodayOrdersDto
import org.android.bbangzip.data.dto.response.ResponseTodoDto
import org.android.bbangzip.data.util.base.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PieceService {
    @GET("/api/v1/pieces/today/orders")
    suspend fun getTodoInfo(
        @Query("area") area: String,
        @Query("year") year: Int,
        @Query("semester") semester: String,
        @Query("sortOption") sortOption: String
    ): BaseResponse<ResponseTodayOrdersDto?>

//    @POST("/api/v1/pieces/{pieceId}/mark-done")
//    suspend fun postCompleteCardId(
//        @Path("pieceId") pieceId: Int,
//        @Body requestMarkDoneDto: RequestMarkDoneDto
//    )

    @GET("/api/v1/pieces/todo")
    suspend fun getAddTodoList(
        @Query("year") year: Int,
        @Query("semester") semester: String,
        @Query("sortOption") sortOption: String
    ): BaseResponse<ResponseTodoDto?>
}