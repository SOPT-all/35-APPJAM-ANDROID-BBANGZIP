package org.android.bbangzip.presentation.ui.todo.todoadd

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.R
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

                TodoAddContract.TodoAddEvent.OnBackIconClick -> {
                    updateState(TodoAddContract.TodoAddReduce.ResetSelectedItemList)
                    setSideEffect(TodoAddContract.TodoAddSideEffect.NavigateToBack)
                }

                TodoAddContract.TodoAddEvent.OnFilterBottomSheetDismissRequest ->
                    updateState(
                        TodoAddContract.TodoAddReduce.UpdateToDoFilterBottomSheetState(
                            isTodoFilterBottomSheetVisible = false,
                        ),
                    )

                TodoAddContract.TodoAddEvent.OnFilterIconClick ->
                    updateState(
                        TodoAddContract.TodoAddReduce.UpdateToDoFilterBottomSheetState(
                            isTodoFilterBottomSheetVisible = true,
                        ),
                    )

                is TodoAddContract.TodoAddEvent.OnFilterBottomSheetItemClick ->
                    getFilteredAddToDoInfo(selectedFilterItem = event.selectedFilterItem)

                TodoAddContract.TodoAddEvent.OnItemPlusBtnClick -> {
                    postAddTodoItemList(selectedItemList = currentUiState.selectedItemList)

                    setSideEffect(TodoAddContract.TodoAddSideEffect.NavigateToToDo)
                    setSideEffect(TodoAddContract.TodoAddSideEffect.ShowSnackBar(R.string.todo_add_plus_study))
                }

                is TodoAddContract.TodoAddEvent.OnToDoCardClick -> {
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
                        isTodoFilterBottomSheetVisible = reduce.isTodoFilterBottomSheetVisible,
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

        private fun getFilteredAddToDoInfo(
            selectedFilterItem: ToDoFilterType,
        ) {
            viewModelScope.launch {
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
                                isTodoFilterBottomSheetVisible = false,
                            ),
                        )
                        setSideEffect(TodoAddContract.TodoAddSideEffect.ShowTodoAddSnackBar(R.string.todo_add_sorted_by_filter, selectedFilterItem.filter))
                    }
                    .onFailure { error ->
                        Timber.tag("todo").d(error)
                    }
            }
        }

        private suspend fun getAddTodoList(selectedFilterItem: ToDoFilterType) =
            getAddTodoListUseCase(
                year = 2025,
                semester = "1학기",
                sortOption = selectedFilterItem.id,
            )

        private fun postAddTodoItemList(
            selectedItemList: List<Int>,
        ) {
            viewModelScope.launch {
                postAddTodoItemListUseCase(
                    requestPieceIdDto = RequestPieceIdDto(pieceIds = selectedItemList),
                ).onSuccess {
                    Timber.tag("assignToToday").d("server viewmodel")
                }.onFailure { error ->
                    Timber.tag("assignToToday").d(error)
                }
            }
        }
    }
