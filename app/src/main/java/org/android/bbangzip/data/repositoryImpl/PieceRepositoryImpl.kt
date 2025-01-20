package org.android.bbangzip.data.repositoryImpl

import org.android.bbangzip.data.datasource.remote.TodayOrdersRemoteDataSource
import org.android.bbangzip.data.datasource.remote.TodoRemoteDataSource
import org.android.bbangzip.domain.model.ToDoInfoEntity
import org.android.bbangzip.domain.repository.remote.PieceRepository
import javax.inject.Inject

class PieceRepositoryImpl
@Inject
constructor(
    private val todayOrdersRemoteDateSource: TodayOrdersRemoteDataSource,
    private val todoRemoteDataSource: TodoRemoteDataSource
) : PieceRepository {
    override suspend fun getTodoInfo(
        area: String,
        year: Int,
        semester: String,
        sortOption: String
    ): Result<ToDoInfoEntity> =
        runCatching {
            todayOrdersRemoteDateSource.getTodoInfo(
                area = area,
                year = year,
                semester = semester,
                sortOption = sortOption
            ).data?.toTodoInfoEntity() ?: ToDoInfoEntity()
        }

    override suspend fun getAddTodoList(
        year: Int,
        semester: String,
        sortOption: String
    ): Result<ToDoInfoEntity> =
        runCatching {
            todoRemoteDataSource.getAddTodolist(
                year = year,
                semester = semester,
                sortOption = sortOption
            ).data?.toTodoCardInfoEntity() ?: ToDoInfoEntity()
        }
}


