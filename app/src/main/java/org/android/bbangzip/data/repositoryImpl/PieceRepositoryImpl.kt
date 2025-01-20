package org.android.bbangzip.data.repositoryImpl

import org.android.bbangzip.data.datasource.remote.AssignToTodayRemoteDataSource
import org.android.bbangzip.data.datasource.remote.HideRemoteDataSource
import org.android.bbangzip.data.datasource.remote.MarkDoneRemoteDataStore
import org.android.bbangzip.data.datasource.remote.MarkUnDoneRemoteDataStore
import org.android.bbangzip.data.datasource.remote.TodayOrdersRemoteDataSource
import org.android.bbangzip.data.datasource.remote.TodoRemoteDataSource
import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.domain.model.BadgeCardListEntity
import org.android.bbangzip.domain.model.ToDoInfoEntity
import org.android.bbangzip.domain.repository.remote.PieceRepository
import javax.inject.Inject

class PieceRepositoryImpl
@Inject
constructor(
    private val todayOrdersRemoteDateSource: TodayOrdersRemoteDataSource,
    private val todoRemoteDataSource: TodoRemoteDataSource,
    private val hideRemoteDataSource: HideRemoteDataSource,
    private val assignToTodayRemoteDataSource: AssignToTodayRemoteDataSource,
    private val markDoneRemoteDataStore: MarkDoneRemoteDataStore,
    private val markUnDoneRemoteDataStore: MarkUnDoneRemoteDataStore
) : PieceRepository {
    override suspend fun getTodoInfo(
        area: String,
        year: Int,
        semester: String,
        sortOption: String
    ): Result<ToDoInfoEntity> =
        runCatching {
            val response = todayOrdersRemoteDateSource.getTodoInfo(
                area = area,
                year = year,
                semester = semester,
                sortOption = sortOption
            )

            val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")

            responseData.toTodoInfoEntity()
        }

    override suspend fun getAddTodoList(
        year: Int,
        semester: String,
        sortOption: String
    ): Result<ToDoInfoEntity> =
        runCatching {
            val response = todoRemoteDataSource.getAddTodolist(
                year = year,
                semester = semester,
                sortOption = sortOption
            )

            val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")

            responseData.toTodoCardInfoEntity()
        }

    override suspend fun postDeletedItemList(requestPieceIdDto: RequestPieceIdDto): Result<Unit> =
        runCatching {
            val response = hideRemoteDataSource.postDeletedItemList(
                requestPieceIdDto = requestPieceIdDto
            )
            response
        }


    override suspend fun postAddTodoItemList(requestPieceIdDto: RequestPieceIdDto): Result<Unit> =
        runCatching {
            val response = assignToTodayRemoteDataSource.postDeletedItemList(
                requestPieceIdDto = requestPieceIdDto
            )
            response
        }

    override suspend fun postCompleteCardID(pieceId: Int, requestMarkDoneDto: RequestMarkDoneDto): Result<BadgeCardListEntity> =
        runCatching {
            val response = markDoneRemoteDataStore.postCompleteCardId(
                pieceId = pieceId, requestMarkDoneDto = requestMarkDoneDto
            )
            val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")
            responseData.toBadgeCardListEntity()
        }

    override suspend fun postUnCompleteCardId(pieceId: Int,requestMarkDoneDto: RequestMarkDoneDto): Result<Unit> =
        runCatching {
            val response = markUnDoneRemoteDataStore.postUnCompleteCardId(
                pieceId = pieceId,
                requestMarkDoneDto = requestMarkDoneDto
            )
            response
        }
}



