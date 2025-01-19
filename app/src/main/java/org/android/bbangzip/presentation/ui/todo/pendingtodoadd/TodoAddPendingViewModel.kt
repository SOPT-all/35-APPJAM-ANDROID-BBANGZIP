package org.android.bbangzip.presentation.ui.todo.pendingtodoadd

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class TodoAddPendingViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<TodoAddPendingContract.TodoAddPendingEvent, TodoAddPendingContract.TodoAddPendingState, TodoAddPendingContract.TodoAddPendingReduce, TodoAddPendingContract.TodoAddPendingSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): TodoAddPendingContract.TodoAddPendingState {
            return savedState as? TodoAddPendingContract.TodoAddPendingState ?: TodoAddPendingContract.TodoAddPendingState()
        }

        override fun handleEvent(event: TodoAddPendingContract.TodoAddPendingEvent) {
            when (event) {
                TodoAddPendingContract.TodoAddPendingEvent.Initialize -> TODO()

                TodoAddPendingContract.TodoAddPendingEvent.OnBackIconClicked -> {
                    updateState(TodoAddPendingContract.TodoAddPendingReduce.ResetSelectedItemList)
                    setSideEffect(TodoAddPendingContract.TodoAddPendingSideEffect.NavigateToBack)
                }

                TodoAddPendingContract.TodoAddPendingEvent.OnFilterBottomSheetDismissRequest ->
                    updateState(
                        TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoFilterBottomSheetState(
                            todoFilterBottomSheetState = false,
                        ),
                    )

                TodoAddPendingContract.TodoAddPendingEvent.OnFilterIconClicked ->
                    updateState(
                        TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoFilterBottomSheetState(
                            todoFilterBottomSheetState = true,
                        ),
                    )

                is TodoAddPendingContract.TodoAddPendingEvent.OnFilterBottomSheetItemClicked -> {
                    updateState(TodoAddPendingContract.TodoAddPendingReduce.UpdateFilterType(selectedFilter = event.selectedFilterItem))
                    updateState(
                        TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoFilterBottomSheetState(
                            todoFilterBottomSheetState = false,
                        ),
                    )
                    setSideEffect(TodoAddPendingContract.TodoAddPendingSideEffect.ShowTodoAddSnackBar("${event.selectedFilterItem.filter}으로 정렬했어요"))
                    // TODO 받아오고 다시 정렬해야됨 ㅋㅋ
                }

                TodoAddPendingContract.TodoAddPendingEvent.OnItemPlusButtonClicked -> {
                    // TODO 서버로 보내기
                    setSideEffect(TodoAddPendingContract.TodoAddPendingSideEffect.NavigateToToDo)
                    setSideEffect(TodoAddPendingContract.TodoAddPendingSideEffect.ShowSnackBar("오늘 할 공부를 추가했어요!"))
                }

                is TodoAddPendingContract.TodoAddPendingEvent.OnToDoCardClicked -> {
                    if (event.cardState == BbangZipCardState.CHECKED) {
                        updateState(TodoAddPendingContract.TodoAddPendingReduce.UpdateSelectedItemList(pieceId = event.pieceId))
                        updateState(
                            TodoAddPendingContract.TodoAddPendingReduce.UpdateCardState(
                                pieceId = event.pieceId,
                                cardState = event.cardState,
                            ),
                        )
                    } else {
                        updateState(TodoAddPendingContract.TodoAddPendingReduce.DeleteSelectedItemList(pieceId = event.pieceId))
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

                is TodoAddPendingContract.TodoAddPendingReduce.UpdateFilterType ->
                    state.copy(
                        selectedFilter = reduce.selectedFilter,
                    )

                is TodoAddPendingContract.TodoAddPendingReduce.UpdateSelectedItemList ->
                    state.copy(
                        selectedItemList = state.selectedItemList.plus(reduce.pieceId),
                    )

                is TodoAddPendingContract.TodoAddPendingReduce.UpdateToDoFilterBottomSheetState ->
                    state.copy(
                        todoFilterBottomSheetState = reduce.todoFilterBottomSheetState,
                    )

                is TodoAddPendingContract.TodoAddPendingReduce.DeleteSelectedItemList ->
                    state.copy(
                        selectedItemList = state.selectedItemList.minus(reduce.pieceId),
                    )
            }
        }
    }
