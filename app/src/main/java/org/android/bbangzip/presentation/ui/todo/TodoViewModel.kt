package org.android.bbangzip.presentation.ui.todo

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.type.ToDoScreenType
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TodoViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<TodoContract.TodoEvent, TodoContract.TodoState, TodoContract.TodoReduce, TodoContract.TodoSideEffect>(
    savedStateHandle = savedStateHandle,
) {
    override fun createInitialState(savedState: Parcelable?): TodoContract.TodoState {
        return savedState as? TodoContract.TodoState ?: TodoContract.TodoState()
    }

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
            is TodoContract.TodoEvent.OnFilterBottomSheetItemClicked -> {
                updateState(TodoContract.TodoReduce.UpdateFilterItemIndex(itemIndex = event.todoFilterItemIndex))
                setSideEffect(TodoContract.TodoSideEffect.ShowSnackBar("${ToDoFilterType.entries[event.todoFilterItemIndex].id}으로 정렬했어요"))
                //TODO index를 이용해 서버로 FetchInfo 이름 보내주기
            }

            TodoContract.TodoEvent.OnFilterBottomSheetDismissRequest -> updateState(TodoContract.TodoReduce.UpdateToDoFilterBottomSheetState(todoFilterBottomSheetState = false))
            TodoContract.TodoEvent.OnFilterIconClicked -> {
                updateState(TodoContract.TodoReduce.UpdateToDoFilterBottomSheetState(todoFilterBottomSheetState = true))

            }

            //revertComplete BottomSheet
            is TodoContract.TodoEvent.OnRevertCompleteBottomSheetApproveButtonClicked -> {
                updateState(TodoContract.TodoReduce.UpdateCardState(pieceId = event.pieceId, cardState = BbangZipCardState.DEFAULT))
                updateState(TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(revertCompleteBottomSheetState = false))
                updateState(TodoContract.TodoReduce.UpdateToDoCount(completeCount = currentUiState.completeCount - 1, remainingStudyCount = currentUiState.remainingStudyCount + 1))
                setSideEffect(TodoContract.TodoSideEffect.ShowSnackBar("미완료 상태로 되돌려졌어요!"))
                //TODO pieceId 사영헤사 서버로 쏘기 미완료 APT
            }

            TodoContract.TodoEvent.OnRevertCompleteBottomSheetDismissButtonClicked -> updateState(TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(revertCompleteBottomSheetState = false))
            TodoContract.TodoEvent.OnRevertCompleteBottomSheetDismissRequest -> updateState(TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(revertCompleteBottomSheetState = false))

            //Delete
            is TodoContract.TodoEvent.OnDeleteScreenCardClicked -> {
                when (event.cardState) {
                    BbangZipCardState.CHECKED -> {
                        updateState(TodoContract.TodoReduce.UpdateSelectedItemList(pieceId = event.pieceId))
                        updateState(TodoContract.TodoReduce.UpdateCardState(pieceId = event.pieceId, cardState = event.cardState))
                    }

                    BbangZipCardState.CHECKABLE -> {
                        updateState(TodoContract.TodoReduce.DeleteSelectedItemList(pieceId = event.pieceId))
                        updateState(TodoContract.TodoReduce.UpdateCardState(pieceId = event.pieceId, cardState = event.cardState))
                    }

                    else -> {
                        setSideEffect(TodoContract.TodoSideEffect.ShowSnackBar("이미 완료한 일은 삭제할 수 없어요!"))
                    }
                }
            }
            //Default
            is TodoContract.TodoEvent.OnDefaultScreenCardClicked -> {
                if (event.cardState == BbangZipCardState.COMPLETE) {
                    updateState(TodoContract.TodoReduce.UpdateCardState(pieceId = event.pieceId, cardState = event.cardState))
                    updateState(TodoContract.TodoReduce.UpdateToDoCount(completeCount = currentUiState.completeCount + 1, remainingStudyCount = currentUiState.remainingStudyCount - 1))
                    TodoContract.TodoSideEffect.ShowSnackBar("공부완료 ! 오늘의 빵굽기 성공!")
                } else {
                    updateState(TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(revertCompleteBottomSheetState = true))
                }
                // TODO 서버로 pieceId 이용해서 완료 API 쏘기
            }
//통과!!
            TodoContract.TodoEvent.OnDeleteIconClicked -> {
                updateState(TodoContract.TodoReduce.UpdateScreenType(screenType = ToDoScreenType.DELETE))
                updateState(
                    TodoContract.TodoReduce.UpdateToDoListCardState(
                        previousCardState = BbangZipCardState.DEFAULT,
                        nextCardState = BbangZipCardState.CHECKABLE
                    )
                )
            }

            TodoContract.TodoEvent.OnItemDeleteButtonClicked -> {
                //TODO setlectedItemlist 사용해서 서버로 삭제한 card API 전송
                updateState(
                    TodoContract.TodoReduce.UpdateToDoCount(
                        completeCount = currentUiState.completeCount,
                        remainingStudyCount = currentUiState.remainingStudyCount - currentUiState.selectedItemList.size
                    )
                )
                if (currentUiState.remainingStudyCount != 0) {
                    updateState(TodoContract.TodoReduce.DeleteToDoListItems)
                    updateState(TodoContract.TodoReduce.UpdateToDoListCardState(previousCardState = BbangZipCardState.CHECKABLE, nextCardState = BbangZipCardState.DEFAULT))
                    updateState(TodoContract.TodoReduce.UpdateScreenType(screenType = ToDoScreenType.DEFAULT))

                } else {
                    updateState(TodoContract.TodoReduce.UpdateScreenType(screenType = ToDoScreenType.EMPTY))
                }
                updateState(TodoContract.TodoReduce.ResetSelectedItemList)
                setSideEffect(TodoContract.TodoSideEffect.ShowSnackBar("오늘 할 공부를 삭제했어요"))
            }
//  통과
            TodoContract.TodoEvent.OnCloseIconClicked -> {
                updateState(TodoContract.TodoReduce.UpdateToDoListCardState(previousCardState = BbangZipCardState.CHECKABLE, nextCardState = BbangZipCardState.DEFAULT))
                updateState(TodoContract.TodoReduce.UpdateToDoListCardState(previousCardState = BbangZipCardState.CHECKED, nextCardState = BbangZipCardState.DEFAULT))
                updateState(TodoContract.TodoReduce.ResetSelectedItemList)
                updateState(TodoContract.TodoReduce.UpdateScreenType(screenType = ToDoScreenType.DEFAULT))
            }

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

            is TodoContract.TodoReduce.UpdateToDoListCardState -> state.copy( // 해당 카드스태이트를 찾아서 바꿔줌
                todoList = state.todoList.map { item ->
                    if (item.cardState == reduce.previousCardState) {
                        item.copy(cardState = reduce.nextCardState)
                    } else item
                }
            )

            is TodoContract.TodoReduce.DeleteToDoListItems -> {
                val pieceIdSet = currentUiState.selectedItemList.toSet()
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

            is TodoContract.TodoReduce.DeleteSelectedItemList -> state.copy(
                selectedItemList = state.selectedItemList.minus(reduce.pieceId)
            )

            //Filter BottomSheet
            is TodoContract.TodoReduce.UpdateToDoFilterBottomSheetState -> state.copy(
                todoFilterBottomSheetState = !state.todoFilterBottomSheetState
            )

            is TodoContract.TodoReduce.UpdateFilterItemIndex -> state.copy(
                todoFilterItemIndex = reduce.itemIndex
            )

            //ToDoCount
            is TodoContract.TodoReduce.UpdateToDoCount -> {
                state.copy(
                    completeCount = reduce.completeCount, remainingStudyCount = reduce.remainingStudyCount
                )
            }

            is TodoContract.TodoReduce.UpdatePendingToDoCount -> state.copy(
                pendingCount = reduce.pendingCount
            )

            //ScreenType
            is TodoContract.TodoReduce.UpdateScreenType -> state.copy(
                screenType = reduce.screenType
            )

            TodoContract.TodoReduce.ResetSelectedItemList -> state.copy(
                selectedItemList = listOf()
            )
        }
    }

    private fun initDataLoad() {

    }


}