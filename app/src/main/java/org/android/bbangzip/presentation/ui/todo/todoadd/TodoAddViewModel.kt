package org.android.bbangzip.presentation.ui.todo.todoadd

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class TodoAddViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<TodoAddContract.TodoAddEvent, TodoAddContract.TodoAddState, TodoAddContract.TodoAddReduce, TodoAddContract.TodoAddSideEffect>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedState: Parcelable?): TodoAddContract.TodoAddState {
        return savedState as? TodoAddContract.TodoAddState ?: TodoAddContract.TodoAddState()
    }

    override fun handleEvent(event: TodoAddContract.TodoAddEvent) {
        when (event) {
            TodoAddContract.TodoAddEvent.Initialize -> TODO()

            TodoAddContract.TodoAddEvent.OnBackIconClicked -> {
                updateState(TodoAddContract.TodoAddReduce.ResetSelectedItemList)
                setSideEffect(TodoAddContract.TodoAddSideEffect.NavigateToBack)
            }

            TodoAddContract.TodoAddEvent.OnFilterBottomSheetDismissRequest -> updateState(
                TodoAddContract.TodoAddReduce.UpdateToDoFilterBottomSheetState(
                    todoFilterBottomSheetState = false
                )
            )

            TodoAddContract.TodoAddEvent.OnFilterIconClicked -> updateState(
                TodoAddContract.TodoAddReduce.UpdateToDoFilterBottomSheetState(
                    todoFilterBottomSheetState = true
                )
            )

            is TodoAddContract.TodoAddEvent.OnFilterBottomSheetItemClicked -> {
                updateState(TodoAddContract.TodoAddReduce.UpdateFilterType(selectedFilter = event.selectedFilterItem))
                updateState(
                    TodoAddContract.TodoAddReduce.UpdateToDoFilterBottomSheetState(
                        todoFilterBottomSheetState = false
                    )
                )
                setSideEffect(TodoAddContract.TodoAddSideEffect.ShowTodoAddSnackBar("${event.selectedFilterItem.filter}으로 정렬했어요"))
                //TODO 받아오고 다시 정렬해야됨 ㅋㅋ
            }

            TodoAddContract.TodoAddEvent.OnItemPlusButtonClicked -> {
                // TODO 서버로 보내기
                setSideEffect(TodoAddContract.TodoAddSideEffect.NavigateToToDo)
                setSideEffect(TodoAddContract.TodoAddSideEffect.ShowSnackBar("오늘 할 공부를 추가했어요!"))
            }

            is TodoAddContract.TodoAddEvent.OnToDoCardClicked -> {
                if (event.cardState == BbangZipCardState.CHECKED) {
                    updateState(TodoAddContract.TodoAddReduce.UpdateSelectedItemList(pieceId = event.pieceId))
                    updateState(
                        TodoAddContract.TodoAddReduce.UpdateCardState(
                            pieceId = event.pieceId,
                            cardState = event.cardState
                        )
                    )
                } else {
                    updateState(TodoAddContract.TodoAddReduce.DeleteSelectedItemList(pieceId = event.pieceId))
                    updateState(
                        TodoAddContract.TodoAddReduce.UpdateCardState(
                            pieceId = event.pieceId,
                            cardState = event.cardState
                        )
                    )
                }
            }
        }
    }

    override fun reduceState(state: TodoAddContract.TodoAddState, reduce: TodoAddContract.TodoAddReduce): TodoAddContract.TodoAddState {
        return when (reduce) {
            TodoAddContract.TodoAddReduce.ResetSelectedItemList -> state.copy(
                selectedItemList = listOf()
            )

            is TodoAddContract.TodoAddReduce.UpdateCardState -> state.copy(
                todoList = state.todoList.map { item ->
                    if (item.pieceId == reduce.pieceId) item.copy(cardState = reduce.cardState) else item
                }
            )

            is TodoAddContract.TodoAddReduce.UpdateFilterType -> state.copy(
                selectedFilter = reduce.selectedFilter
            )

            is TodoAddContract.TodoAddReduce.UpdateSelectedItemList -> state.copy(
                selectedItemList = state.selectedItemList.plus(reduce.pieceId)
            )

            is TodoAddContract.TodoAddReduce.UpdateToDoFilterBottomSheetState -> state.copy(
                todoFilterBottomSheetState = reduce.todoFilterBottomSheetState
            )

            is TodoAddContract.TodoAddReduce.DeleteSelectedItemList -> state.copy(
                selectedItemList = state.selectedItemList.minus(reduce.pieceId)
            )
        }
    }
}