package org.android.bbangzip.presentation.ui.todo

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.type.ToDoScreenType
import org.android.bbangzip.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<TodoContract.TodoEvent, TodoContract.TodoState, TodoContract.TodoReduce, TodoContract.TodoSideEffect>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedState: Parcelable?): TodoContract.TodoState = TodoContract.TodoState()

    init {
        setEvent(TodoContract.TodoEvent.Initialize)
    }

    override fun handleEvent(event: TodoContract.TodoEvent) {
        when (event) {
            //ToDoInfo Fetch
            is TodoContract.TodoEvent.FetchToDoInfo -> updateState(
                TodoContract.TodoReduce.UpdateToDoInfo(
                    pendingCount = event.pendingCount,
                    remainingStudyCount = event.remainingStudyCount,
                    completeCount = event.completeCount,
                    todoList = event.todoList
                )
            )

            TodoContract.TodoEvent.Initialize -> launch { initDataLoad() }

            //Filter BottomSheet
            is TodoContract.TodoEvent.OnFilterItemClicked -> updateState(
                TodoContract.TodoReduce.UpdateFilterItemIndex(itemIndex = event.todoFilterItemIndex)
            )

            TodoContract.TodoEvent.OnFilterDismissRequest -> updateState(TodoContract.TodoReduce.UpdateToDoFilterBottomSheetState(todoFilterBottomSheetState = false))
            TodoContract.TodoEvent.OnFilterIconClicked -> updateState(TodoContract.TodoReduce.UpdateToDoFilterBottomSheetState(todoFilterBottomSheetState = true))

            //revertComplete BottomSheet
            is TodoContract.TodoEvent.OnRevertCompleteApproveButtonClicked -> {
                updateState(TodoContract.TodoReduce.UpdateCardState(pieceId = event.pieceId, cardState = event.cardState))
                updateState(TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(revertCompleteBottomSheetState = false))
            }

            TodoContract.TodoEvent.OnRevertCompleteDismissButtonClicked -> updateState(TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(revertCompleteBottomSheetState = false))
            TodoContract.TodoEvent.OnRevertCompleteDismissRequest -> updateState(TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(revertCompleteBottomSheetState = false))

            //Delete
            is TodoContract.TodoEvent.OnDeleteScreenCardClicked ->
                updateState(TodoContract.TodoReduce.UpdateSelectedItemList(pieceId = event.pieceId))


            TodoContract.TodoEvent.OnDeleteIconClicked -> updateState(TodoContract.TodoReduce.UpdateScreenType(screenType = ToDoScreenType.DELETE))
            TodoContract.TodoEvent.OnItemDeleteButtonClicked -> updateState(TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(revertCompleteBottomSheetState = true))
            TodoContract.TodoEvent.OnCloseIconClicked -> updateState(TodoContract.TodoReduce.UpdateScreenType(screenType = ToDoScreenType.FETCH))

            //화면 이동
            TodoContract.TodoEvent.OnAddStudyButtonClicked -> setSideEffect(TodoContract.TodoSideEffect.NavigateToAddToDo)
            TodoContract.TodoEvent.OnAddPendingStudyButtonClicked -> setSideEffect(TodoContract.TodoSideEffect.NavigateToAddPendingToDo)
        }
    }

    override fun reduceState(state: TodoContract.TodoState, reduce: TodoContract.TodoReduce): TodoContract.TodoState {
        return when (reduce) {
            //ToDoInfo Fetch
            is TodoContract.TodoReduce.UpdateToDoInfo -> state.copy(
                pendingCount = reduce.pendingCount,
                completeCount = reduce.completeCount,
                remainingStudyCount = reduce.remainingStudyCount,
                todoList = reduce.todoList
            )

            //List
            is TodoContract.TodoReduce.UpdateCardState -> state.copy(
                todoList = state.todoList.map { item ->
                    if (item.pieceId == reduce.pieceId) item.copy(cardState = reduce.cardState) else item
                }
            )

            is TodoContract.TodoReduce.UpdateToDoListState -> state.copy(
                todoList = state.todoList.map { item ->
                    item.copy(cardState = reduce.cardState)
                }
            )

            is TodoContract.TodoReduce.DeleteToDoListItems -> {
                val pieceIdSet = reduce.pieceIds.toSet()
                state.copy(
                    todoList = state.todoList.filter { item ->
                        item.pieceId !in pieceIdSet
                    }
                )
            }

            //Revert BottomSheet
            is TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState -> state.copy(
                revertCompleteBottomSheetState = !state.revertCompleteBottomSheetState
            )

            is TodoContract.TodoReduce.UpdateSelectedItemList -> state.copy(
                selectedItemList = state.selectedItemList.plus(reduce.pieceId)
            )

            //Filter BottomSheet
            is TodoContract.TodoReduce.UpdateToDoFilterBottomSheetState -> state.copy(
                todoFilterBottomSheetState = !state.todoFilterBottomSheetState
            )

            is TodoContract.TodoReduce.UpdateFilterItemIndex -> state.copy(
                todoFilterItemIndex = reduce.itemIndex
            )

            //ToDoCount
            is TodoContract.TodoReduce.UpdateToDoCount -> state.copy(
                completeCount = reduce.completeCount, remainingStudyCount = reduce.remainingStudyCount
            )

            is TodoContract.TodoReduce.UpdatePendingToDoCount -> state.copy(
                pendingCount = reduce.pendingCount
            )

            //ScreenType
            is TodoContract.TodoReduce.UpdateScreenType -> state.copy(
                screenType = reduce.screenType
            )


        }
    }

    private suspend fun initDataLoad() {

    }


}