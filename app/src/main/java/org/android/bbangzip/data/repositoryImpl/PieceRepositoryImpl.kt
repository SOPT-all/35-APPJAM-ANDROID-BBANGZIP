package org.android.bbangzip.data.repositoryImpl

import org.android.bbangzip.data.datasource.remote.PieceRemoteDataSource
import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.domain.model.BadgeCardListEntity
import org.android.bbangzip.domain.model.ToDoInfoEntity
import org.android.bbangzip.domain.repository.remote.PieceRepository
import javax.inject.Inject

class PieceRepositoryImpl
    @Inject
    constructor(
        private val pieceRemoteDataSource: PieceRemoteDataSource,
    ) : PieceRepository {
        override suspend fun getTodoInfo(
            area: String,
            year: Int,
            semester: String,
            sortOption: String,
        ): Result<ToDoInfoEntity> =
            runCatching {
                val response =
                    pieceRemoteDataSource.getTodoInfo(
                        area = area,
                        year = year,
                        semester = semester,
                        sortOption = sortOption,
                    )

                val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")

                responseData.toTodoInfoEntity()
            }

        override suspend fun getAddTodoList(
            year: Int,
            semester: String,
            sortOption: String,
        ): Result<ToDoInfoEntity> =
            runCatching {
                val response =
                    pieceRemoteDataSource.getAddTodolist(
                        year = year,
                        semester = semester,
                        sortOption = sortOption,
                    )

                val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")

                responseData.toTodoCardInfoEntity()
            }

        override suspend fun postDeletedItemList(requestPieceIdDto: RequestPieceIdDto): Result<Unit> =
            runCatching {
                val response =
                    pieceRemoteDataSource.postDeletedItemList(
                        requestPieceIdDto = requestPieceIdDto,
                    )
                response
            }

        override suspend fun postAddTodoItemList(requestPieceIdDto: RequestPieceIdDto): Result<Unit> =
            runCatching {
                val response =
                    pieceRemoteDataSource.postAddTodoItemList(
                        requestPieceIdDto = requestPieceIdDto,
                    )
                response
            }

        override suspend fun postCompleteCardID(
            pieceId: Int,
            requestMarkDoneDto: RequestMarkDoneDto,
        ): Result<BadgeCardListEntity> =
            runCatching {
                val response =
                    pieceRemoteDataSource.postCompleteCardId(
                        pieceId = pieceId,
                        requestMarkDoneDto = requestMarkDoneDto,
                    )
                val responseData = response.data ?: throw IllegalStateException(response.message ?: "Null Error")
                responseData.toBadgeCardListEntity()
            }

        override suspend fun postUnCompleteCardId(
            pieceId: Int,
            requestMarkDoneDto: RequestMarkDoneDto,
        ): Result<Unit> =
            runCatching {
                val response =
                    pieceRemoteDataSource.postUnCompleteCardId(
                        pieceId = pieceId,
                        requestMarkDoneDto = requestMarkDoneDto,
                    )
                response
            }
    }
