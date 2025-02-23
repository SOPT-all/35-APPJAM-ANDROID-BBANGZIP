package org.android.bbangzip.presentation.ui.todo.pendingtodoadd

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.R
import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.domain.usecase.GetToInfoUseCase
import org.android.bbangzip.domain.usecase.PostAddTodoItemListUseCase
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.util.base.BaseViewModel
import org.android.bbangzip.presentation.util.constant.ToDoConstants
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TodoAddPendingViewModel
    @Inject
    constructor(
        private val getTodoInfoUseCase: GetToInfoUseCase,
        private val postAddTodoItemListUseCase: PostAddTodoItemListUseCase,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<TodoAddPendingContract.TodoAddPendingEvent, TodoAddPendingContract.TodoAddPendingState, TodoAddPendingContract.TodoAddPendingReduce, TodoAddPendingContract.TodoAddPendingSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): TodoAddPendingContract.TodoAddPendingState {
            return savedState as? TodoAddPendingContract.TodoAddPendingState
                ?: TodoAddPendingContract.TodoAddPendingState()
        }

        init {
            setEvent(TodoAddPendingContract.TodoAddPendingEvent.Initialize)
        }

        override fun handleEvent(event: TodoAddPendingContract.TodoAddPendingEvent) {
            when (event) {
                TodoAddPendingContract.TodoAddPendingEvent.Initialize -> launch { initDataLoad() }

                TodoAddPendingContract.TodoAddPendingEvent.OnBackIconClick -> {
                    updateState(TodoAddPendingContract.TodoAddPendingReduce.ResetSelectedItemList)
                    setSideEffect(TodoAddPendingContract.TodoAddPendingSideEffect.NavigateToBack)
                }

                TodoAddPendingContract.TodoAddPendingEvent.OnFilterBottomSheetDismissRequest ->
                    updateState(
                        TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoFilterBottomSheetState(
                            isTodoFilterBottomSheetVisible = false,
                        ),
                    )

                TodoAddPendingContract.TodoAddPendingEvent.OnFilterIconClick ->
                    updateState(
                        TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoFilterBottomSheetState(
                            isTodoFilterBottomSheetVisible = true,
                        ),
                    )

                is TodoAddPendingContract.TodoAddPendingEvent.OnFilterBottomSheetItemClick -> {
                    getFilteredToDoInfo(
                        selectedFilter = event.selectedFilterItem,
                    )
                }

                TodoAddPendingContract.TodoAddPendingEvent.OnItemPlusBtnClick -> {
                    postAddTodoItemList(selectedItemList = currentUiState.selectedItemList)

                    setSideEffect(TodoAddPendingContract.TodoAddPendingSideEffect.NavigateToToDo)
                    setSideEffect(TodoAddPendingContract.TodoAddPendingSideEffect.ShowSnackbar(R.string.todo_pending_add_plus_study))
                }

                is TodoAddPendingContract.TodoAddPendingEvent.OnToDoCardClick -> {
                    if (event.cardState == BbangZipCardState.CHECKED) {
                        updateState(
                            TodoAddPendingContract.TodoAddPendingReduce.UpdateSelectedItemList(
                                pieceId = event.pieceId,
                            ),
                        )
                        updateState(
                            TodoAddPendingContract.TodoAddPendingReduce.UpdateCardState(
                                pieceId = event.pieceId,
                                cardState = event.cardState,
                            ),
                        )
                    } else {
                        updateState(
                            TodoAddPendingContract.TodoAddPendingReduce.DeleteSelectedItemList(
                                pieceId = event.pieceId,
                            ),
                        )
                        updateState(
                            TodoAddPendingContract.TodoAddPendingReduce.UpdateCardState(
                                pieceId = event.pieceId,
                                cardState = event.cardState,
                            ),
                        )
                    }
                }
            }
        }

        override fun reduceState(
            state: TodoAddPendingContract.TodoAddPendingState,
            reduce: TodoAddPendingContract.TodoAddPendingReduce,
        ): TodoAddPendingContract.TodoAddPendingState {
            return when (reduce) {
                TodoAddPendingContract.TodoAddPendingReduce.ResetSelectedItemList ->
                    state.copy(
                        selectedItemList = listOf(),
                    )

                is TodoAddPendingContract.TodoAddPendingReduce.UpdateCardState ->
                    state.copy(
                        todoList =
                            state.todoList.map { item ->
                                if (item.pieceId == reduce.pieceId) item.copy(cardState = reduce.cardState) else item
                            },
                    )

                is TodoAddPendingContract.TodoAddPendingReduce.UpdateFilterType -> {
                    state.copy(
                        selectedFilter = reduce.selectedFilter,
                    )
                }

                is TodoAddPendingContract.TodoAddPendingReduce.UpdateSelectedItemList ->
                    state.copy(
                        selectedItemList = state.selectedItemList.plus(reduce.pieceId),
                    )

                is TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoFilterBottomSheetState ->
                    state.copy(
                        isTodoFilterBottomSheetVisible = reduce.isTodoFilterBottomSheetVisible,
                    )

                is TodoAddPendingContract.TodoAddPendingReduce.DeleteSelectedItemList ->
                    state.copy(
                        selectedItemList = state.selectedItemList.minus(reduce.pieceId),
                    )

                is TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoList ->
                    state.copy(
                        todoList = reduce.todoList,
                    )
            }
        }

        private suspend fun initDataLoad() {
            getToDoInfo(
                area = ToDoConstants.PENDING,
                year = 2025,
                semester = "1학기",
                sortOption = currentUiState.selectedFilter.id,
            ).onSuccess { data ->
                Timber.tag("todayOrders").d("${currentUiState.selectedItemList}")
                updateState(
                    TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoList(
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
                                    cardState = BbangZipCardState.CHECKABLE,
                                )
                            },
                    ),
                )
            }.onFailure { error ->
                Timber.tag("todayOrders").e(error)
            }
        }

        private fun getFilteredToDoInfo(selectedFilter: ToDoFilterType) {
            viewModelScope.launch {
                getToDoInfo(
                    area = ToDoConstants.PENDING,
                    year = 2025,
                    semester = "1학기",
                    sortOption = selectedFilter.id,
                ).onSuccess { data ->
                    Timber.tag("todayOrders").d("${currentUiState.selectedItemList}")
                    updateState(
                        TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoList(
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
                    updateState(
                        TodoAddPendingContract.TodoAddPendingReduce.UpdateFilterType(
                            selectedFilter = selectedFilter,
                        ),
                    )
                    updateState(
                        TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoFilterBottomSheetState(
                            isTodoFilterBottomSheetVisible = false,
                        ),
                    )

                    setSideEffect(TodoAddPendingContract.TodoAddPendingSideEffect.ShowTodoAddSnackbar(R.string.todo_pending_add_sorted_by_filter, selectedFilter.filter))
                }.onFailure { error ->
                    Timber.tag("todayOrders").e(error)
                }
            }
        }

        private suspend fun getToDoInfo(
            area: String,
            year: Int,
            semester: String,
            sortOption: String,
        ) =
            getTodoInfoUseCase(
                area = area,
                year = year,
                semester = semester,
                sortOption = sortOption,
            )

        private fun postAddTodoItemList(
            selectedItemList: List<Int>,
        ) {
            viewModelScope.launch {
                postAddTodoItemListUseCase(
                    requestPieceIdDto = RequestPieceIdDto(pieceIds = selectedItemList),
                ).onSuccess {
                    Timber.tag("postAdd").d("성공")
                }.onFailure { error ->
                    Timber.tag("postAdd").d(error)
                }
            }
        }
    }
