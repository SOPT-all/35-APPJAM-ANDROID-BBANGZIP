package org.android.bbangzip.presentation.ui.todo.todoadd

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.domain.usecase.GetAddTodoListUseCase
import org.android.bbangzip.domain.usecase.PostAddTodoItemListUseCase
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TodoAddViewModel
@Inject
constructor(
    private val getAddTodoListUseCase: GetAddTodoListUseCase,
    private val postAddTodoItemListUseCase: PostAddTodoItemListUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<TodoAddContract.TodoAddEvent, TodoAddContract.TodoAddState, TodoAddContract.TodoAddReduce, TodoAddContract.TodoAddSideEffect>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedState: Parcelable?): TodoAddContract.TodoAddState {
        return savedState as? TodoAddContract.TodoAddState ?: TodoAddContract.TodoAddState()
    }

    init {
        setEvent(TodoAddContract.TodoAddEvent.Initialize)
    }

    override fun handleEvent(event: TodoAddContract.TodoAddEvent) {
        when (event) {
            TodoAddContract.TodoAddEvent.Initialize -> launch { initDataLoad() }

            TodoAddContract.TodoAddEvent.OnBackIconClicked -> {
                updateState(TodoAddContract.TodoAddReduce.ResetSelectedItemList)
                setSideEffect(TodoAddContract.TodoAddSideEffect.NavigateToBack)
            }

            TodoAddContract.TodoAddEvent.OnFilterBottomSheetDismissRequest ->
                updateState(
                    TodoAddContract.TodoAddReduce.UpdateToDoFilterBottomSheetState(
                        todoFilterBottomSheetState = false,
                    ),
                )

            TodoAddContract.TodoAddEvent.OnFilterIconClicked ->
                updateState(
                    TodoAddContract.TodoAddReduce.UpdateToDoFilterBottomSheetState(
                        todoFilterBottomSheetState = true,
                    ),
                )

            is TodoAddContract.TodoAddEvent.OnFilterBottomSheetItemClicked -> {
                viewModelScope.launch {
                    getFilteredAddToDoInfo(selectedFilterItem = event.selectedFilterItem)
                }
            }

            TodoAddContract.TodoAddEvent.OnItemPlusButtonClicked -> {
                viewModelScope.launch {
                    postAddTodoItemList(selectedItemList = currentUiState.selectedItemList)
                }
                setSideEffect(TodoAddContract.TodoAddSideEffect.NavigateToToDo)
                setSideEffect(TodoAddContract.TodoAddSideEffect.ShowSnackBar("오늘 할 공부를 추가했어요!"))
            }

            is TodoAddContract.TodoAddEvent.OnToDoCardClicked -> {
                if (event.cardState == BbangZipCardState.CHECKED) {
                    updateState(TodoAddContract.TodoAddReduce.UpdateSelectedItemList(pieceId = event.pieceId))
                    updateState(
                        TodoAddContract.TodoAddReduce.UpdateCardState(
                            pieceId = event.pieceId,
                            cardState = event.cardState,
                        ),
                    )
                } else {
                    updateState(TodoAddContract.TodoAddReduce.DeleteSelectedItemList(pieceId = event.pieceId))
                    updateState(
                        TodoAddContract.TodoAddReduce.UpdateCardState(
                            pieceId = event.pieceId,
                            cardState = event.cardState,
                        ),
                    )
                }
            }
        }
    }

    override fun reduceState(
        state: TodoAddContract.TodoAddState,
        reduce: TodoAddContract.TodoAddReduce,
    ): TodoAddContract.TodoAddState {
        return when (reduce) {
            TodoAddContract.TodoAddReduce.ResetSelectedItemList ->
                state.copy(
                    selectedItemList = listOf(),
                )

            is TodoAddContract.TodoAddReduce.UpdateCardState ->
                state.copy(
                    todoList =
                    state.todoList.map { item ->
                        if (item.pieceId == reduce.pieceId) item.copy(cardState = reduce.cardState) else item
                    },
                )

            is TodoAddContract.TodoAddReduce.UpdateFilterType ->
                state.copy(
                    selectedFilter = reduce.selectedFilter,
                )

            is TodoAddContract.TodoAddReduce.UpdateSelectedItemList ->
                state.copy(
                    selectedItemList = state.selectedItemList.plus(reduce.pieceId),
                )

            is TodoAddContract.TodoAddReduce.UpdateToDoFilterBottomSheetState ->
                state.copy(
                    todoFilterBottomSheetState = reduce.todoFilterBottomSheetState,
                )

            is TodoAddContract.TodoAddReduce.DeleteSelectedItemList ->
                state.copy(
                    selectedItemList = state.selectedItemList.minus(reduce.pieceId),
                )

            is TodoAddContract.TodoAddReduce.UpdateToDoList ->
                state.copy(
                    todoList = reduce.todoList,
                )
        }
    }

    private suspend fun initDataLoad() {
        getAddTodoList(
            selectedFilterItem = currentUiState.selectedFilter,
        ).onSuccess { data ->
            Timber.tag("todo").d("server viewmodel")
            updateState(
                TodoAddContract.TodoAddReduce.UpdateToDoList(
                    todoList =
                    data.todoList.map { item ->
                        ToDoCardModel(
                            pieceId = item.pieceId,
                            subjectName = item.subjectName,
                            examName = item.examName,
                            studyContents = item.studyContents,
                            startPage = item.startPage,
                            finishPage = item.finishPage,
                            deadline = item.deadline,
                            remainingDays = item.remainingDays,
                            cardState =
                            if (currentUiState.selectedItemList.toSet()
                                    .contains(item.pieceId)
                            ) {
                                BbangZipCardState.CHECKED
                            } else {
                                BbangZipCardState.CHECKABLE
                            },
                        )
                    },
                ),
            )
        }
            .onFailure { error ->
                Timber.tag("todo").d(error)
            }
    }

    private suspend fun getFilteredAddToDoInfo(
        selectedFilterItem: ToDoFilterType,
    ) {
        getAddTodoList(selectedFilterItem)
            .onSuccess { data ->
                Timber.tag("todo").d("server viewmodel")
                Timber.tag("todo").d(selectedFilterItem.id)

                updateState(
                    TodoAddContract.TodoAddReduce.UpdateToDoList(
                        todoList =
                        data.todoList.map { item ->
                            ToDoCardModel(
                                pieceId = item.pieceId,
                                subjectName = item.subjectName,
                                examName = item.examName,
                                studyContents = item.studyContents,
                                startPage = item.startPage,
                                finishPage = item.finishPage,
                                deadline = item.deadline,
                                remainingDays = item.remainingDays,
                                cardState =
                                if (currentUiState.selectedItemList.toSet()
                                        .contains(item.pieceId)
                                ) {
                                    BbangZipCardState.CHECKED
                                } else {
                                    BbangZipCardState.CHECKABLE
                                },
                            )
                        },
                    ),
                )
                updateState(TodoAddContract.TodoAddReduce.UpdateFilterType(selectedFilter = selectedFilterItem))
                updateState(
                    TodoAddContract.TodoAddReduce.UpdateToDoFilterBottomSheetState(
                        todoFilterBottomSheetState = false,
                    ),
                )
                setSideEffect(TodoAddContract.TodoAddSideEffect.ShowTodoAddSnackBar("${selectedFilterItem.filter}으로 정렬했어요"))

            }
            .onFailure { error ->
                Timber.tag("todo").d(error)
            }
    }


    private suspend fun getAddTodoList(selectedFilterItem: ToDoFilterType) =
        getAddTodoListUseCase(
            year = 2025,
            semester = "1학기",
            sortOption = selectedFilterItem.id,
        )

    private suspend fun postAddTodoItemList(
        selectedItemList: List<Int>,
    ) {
        postAddTodoItemListUseCase(
            requestPieceIdDto = RequestPieceIdDto(pieceIds = selectedItemList),
        ).onSuccess {
            Timber.tag("assignToToday").d("server viewmodel")
        }.onFailure { error ->
            Timber.tag("assignToToday").d(error)
        }
    }
}
