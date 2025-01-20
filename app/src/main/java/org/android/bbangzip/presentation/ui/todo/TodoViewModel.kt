package org.android.bbangzip.presentation.ui.todo

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.data.dto.request.RequestPieceIdDto
import org.android.bbangzip.domain.usecase.GetToInfoUseCase
import org.android.bbangzip.domain.usecase.PostCompleteCardIdUseCase
import org.android.bbangzip.domain.usecase.PostDeletedItemListUseCase
import org.android.bbangzip.domain.usecase.PostUnCompleteCardIdUseCase
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoScreenType
import org.android.bbangzip.presentation.util.base.BaseViewModel
import org.android.bbangzip.presentation.util.constant.ToDoConstants
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TodoViewModel
@Inject
constructor(
    private val getTodoInfoUseCase: GetToInfoUseCase,
    private val postDeletedItemListUseCase: PostDeletedItemListUseCase,
    private val postCompleteCardIdUseCase: PostCompleteCardIdUseCase,
    private val postUnCompleteCardIdUseCase: PostUnCompleteCardIdUseCase,
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
            // ToDoInfo Fetch
            is TodoContract.TodoEvent.FetchToDoInfo ->
                updateState(
                    TodoContract.TodoReduce.UpdateToDoInfo(
                        pendingCount = event.pendingCount,
                        remainingStudyCount = event.remainingStudyCount,
                        completeCount = event.completeCount,
                        todoList = event.todoList,
                        screenType = event.screenType
                    ),
                )

            TodoContract.TodoEvent.Initialize -> launch { initDataLoad() }

            // Filter BottomSheet
            is TodoContract.TodoEvent.OnFilterBottomSheetItemClicked -> {
                viewModelScope.launch {
                    initDataLoad()
                }
                updateState(TodoContract.TodoReduce.UpdateFilterType(selectedFilter = event.selectedFilterItem))
                updateState(
                    TodoContract.TodoReduce.UpdateToDoFilterBottomSheetState(
                        todoFilterBottomSheetState = false,
                    )
                )
                setSideEffect(TodoContract.TodoSideEffect.ShowSnackBar("${event.selectedFilterItem.filter}으로 정렬했어요"))
            }

            TodoContract.TodoEvent.OnFilterBottomSheetDismissRequest ->
                updateState(
                    TodoContract.TodoReduce.UpdateToDoFilterBottomSheetState(
                        todoFilterBottomSheetState = false,
                    ),
                )

            TodoContract.TodoEvent.OnFilterIconClicked -> {
                updateState(
                    TodoContract.TodoReduce.UpdateToDoFilterBottomSheetState(
                        todoFilterBottomSheetState = true,
                    ),
                )
            }

            // revertComplete BottomSheet
            is TodoContract.TodoEvent.OnRevertCompleteBottomSheetApproveButtonClicked -> {
                viewModelScope.launch {
                    postUnCompleteCardId(event.pieceId)
                }
                updateState(
                    TodoContract.TodoReduce.UpdateCardState(
                        pieceId = event.pieceId,
                        cardState = BbangZipCardState.DEFAULT,
                    ),
                )
                updateState(
                    TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(
                        revertCompleteBottomSheetState = false,
                    ),
                )
                updateState(
                    TodoContract.TodoReduce.UpdateToDoCount(
                        completeCount = currentUiState.completeCount - 1,
                        remainingStudyCount = currentUiState.remainingStudyCount + 1,
                    ),
                )
                updateState(TodoContract.TodoReduce.ResetSelectedItemList)
                setSideEffect(TodoContract.TodoSideEffect.ShowSnackBar("미완료 상태로 되돌려졌어요!"))
                // TODO pieceId 사영헤사 서버로 쏘기 미완료 APT
            }

            TodoContract.TodoEvent.OnRevertCompleteBottomSheetDismissButtonClicked -> {
                updateState(
                    TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(
                        revertCompleteBottomSheetState = false,
                    ),
                )
                updateState(TodoContract.TodoReduce.ResetSelectedItemList)
            }

            TodoContract.TodoEvent.OnRevertCompleteBottomSheetDismissRequest -> {
                updateState(
                    TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(
                        revertCompleteBottomSheetState = false,
                    ),
                )
                updateState(TodoContract.TodoReduce.ResetSelectedItemList)
            }

            // Delete
            is TodoContract.TodoEvent.OnDeleteScreenCardClicked -> {
                when (event.cardState) {
                    BbangZipCardState.CHECKED -> {
                        updateState(TodoContract.TodoReduce.UpdateSelectedItemList(pieceId = event.pieceId))
                        updateState(
                            TodoContract.TodoReduce.UpdateCardState(
                                pieceId = event.pieceId,
                                cardState = event.cardState,
                            ),
                        )
                    }

                    BbangZipCardState.CHECKABLE -> {
                        updateState(TodoContract.TodoReduce.DeleteSelectedItemList(pieceId = event.pieceId))
                        updateState(
                            TodoContract.TodoReduce.UpdateCardState(
                                pieceId = event.pieceId,
                                cardState = event.cardState,
                            ),
                        )
                    }

                    else -> {
                        setSideEffect(TodoContract.TodoSideEffect.ShowSnackBar("이미 완료한 일은 삭제할 수 없어요!"))
                    }
                }
            }
            // Default
            is TodoContract.TodoEvent.OnDefaultScreenCardClicked -> {
                if (event.cardState == BbangZipCardState.COMPLETE) {
                    updateState(
                        TodoContract.TodoReduce.UpdateCardState(
                            pieceId = event.pieceId,
                            cardState = event.cardState,
                        ),
                    )
                    updateState(
                        TodoContract.TodoReduce.UpdateToDoCount(
                            completeCount = currentUiState.completeCount + 1,
                            remainingStudyCount = currentUiState.remainingStudyCount - 1,
                        ),
                    )
                    viewModelScope.launch {
                        postCompleteCardId(pieceId = event.pieceId)
                    }
                    setSideEffect(TodoContract.TodoSideEffect.ShowSnackBar("공부완료 ! 오늘의 빵굽기 성공!"))
                } else {
                    updateState(
                        TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState(
                            revertCompleteBottomSheetState = true,
                        ),
                    )
                    updateState(TodoContract.TodoReduce.UpdateSelectedItemList(pieceId = event.pieceId))
                }
            }

            TodoContract.TodoEvent.OnDeleteIconClicked -> {
                updateState(TodoContract.TodoReduce.UpdateScreenType(screenType = ToDoScreenType.DELETE))
                updateState(
                    TodoContract.TodoReduce.UpdateToDoListCardState(
                        previousCardState = BbangZipCardState.DEFAULT,
                        nextCardState = BbangZipCardState.CHECKABLE,
                    ),
                )
            }

            TodoContract.TodoEvent.OnItemDeleteButtonClicked -> {
                viewModelScope.launch {
                    postDeletedItemList(selectedItemList = currentUiState.selectedItemList)
                }
                updateState(
                    TodoContract.TodoReduce.UpdateToDoCount(
                        completeCount = currentUiState.completeCount,
                        remainingStudyCount = currentUiState.remainingStudyCount - currentUiState.selectedItemList.size,
                    ),
                )
                updateState(
                    TodoContract.TodoReduce.UpdatePendingToDoCount(
                        pendingCount = currentUiState.pendingCount + currentUiState.selectedItemList.size
                    ),
                )

                if (currentUiState.completeCount > 0 || currentUiState.remainingStudyCount - currentUiState.selectedItemList.size != 0) {
                    updateState(TodoContract.TodoReduce.DeleteToDoListItems)
                    updateState(
                        TodoContract.TodoReduce.UpdateToDoListCardState(
                            previousCardState = BbangZipCardState.CHECKABLE,
                            nextCardState = BbangZipCardState.DEFAULT,
                        ),
                    )
                    updateState(TodoContract.TodoReduce.UpdateScreenType(screenType = ToDoScreenType.DEFAULT))
                } else {
                    updateState(TodoContract.TodoReduce.UpdateScreenType(screenType = ToDoScreenType.EMPTY))
                }



                updateState(TodoContract.TodoReduce.ResetSelectedItemList)
                setSideEffect(TodoContract.TodoSideEffect.ShowSnackBar("오늘 할 공부를 삭제했어요"))
            }
//  통과
            TodoContract.TodoEvent.OnCloseIconClicked -> {
                updateState(
                    TodoContract.TodoReduce.UpdateToDoListCardState(
                        previousCardState = BbangZipCardState.CHECKABLE,
                        nextCardState = BbangZipCardState.DEFAULT,
                    ),
                )
                updateState(
                    TodoContract.TodoReduce.UpdateToDoListCardState(
                        previousCardState = BbangZipCardState.CHECKED,
                        nextCardState = BbangZipCardState.DEFAULT,
                    ),
                )
                updateState(TodoContract.TodoReduce.ResetSelectedItemList)
                updateState(TodoContract.TodoReduce.UpdateScreenType(screenType = ToDoScreenType.DEFAULT))
            }

            // 화면 이동
            TodoContract.TodoEvent.OnAddStudyButtonClicked -> setSideEffect(TodoContract.TodoSideEffect.NavigateToAddToDo)
            TodoContract.TodoEvent.OnAddPendingStudyButtonClicked -> setSideEffect(TodoContract.TodoSideEffect.NavigateToAddPendingToDo)
        }
    }

    override fun reduceState(
        state: TodoContract.TodoState,
        reduce: TodoContract.TodoReduce,
    ): TodoContract.TodoState {
        return when (reduce) {
            // ToDoInfo Fetch
            is TodoContract.TodoReduce.UpdateToDoInfo ->
                state.copy(
                    pendingCount = reduce.pendingCount,
                    completeCount = reduce.completeCount,
                    remainingStudyCount = reduce.remainingStudyCount,
                    todoList = reduce.todoList,
                    screenType = reduce.screenType
                )

            // List
            is TodoContract.TodoReduce.UpdateCardState ->
                state.copy(
                    todoList =
                    state.todoList.map { item ->
                        if (item.pieceId == reduce.pieceId) item.copy(cardState = reduce.cardState) else item
                    },
                )

            is TodoContract.TodoReduce.UpdateToDoListCardState ->
                state.copy(
                    todoList =
                    state.todoList.map { item ->
                        if (item.cardState == reduce.previousCardState) {
                            item.copy(cardState = reduce.nextCardState)
                        } else {
                            item
                        }
                    },
                )

            is TodoContract.TodoReduce.DeleteToDoListItems -> {
                val pieceIdSet = currentUiState.selectedItemList.toSet()
                state.copy(
                    todoList =
                    state.todoList.filter { item ->
                        item.pieceId !in pieceIdSet
                    },
                )
            }

            // Revert BottomSheet
            is TodoContract.TodoReduce.UpdateRevertCompleteBottomSheetState ->
                state.copy(
                    revertCompleteBottomSheetState = reduce.revertCompleteBottomSheetState,
                )

            is TodoContract.TodoReduce.UpdateSelectedItemList ->
                state.copy(
                    selectedItemList = state.selectedItemList.plus(reduce.pieceId),
                )

            is TodoContract.TodoReduce.DeleteSelectedItemList ->
                state.copy(
                    selectedItemList = state.selectedItemList.minus(reduce.pieceId),
                )

            // Filter BottomSheet
            is TodoContract.TodoReduce.UpdateToDoFilterBottomSheetState ->
                state.copy(
                    todoFilterBottomSheetState = reduce.todoFilterBottomSheetState,
                )

            is TodoContract.TodoReduce.UpdateFilterType ->
                state.copy(
                    selectedFilterItem = reduce.selectedFilter,
                )

            // ToDoCount
            is TodoContract.TodoReduce.UpdateToDoCount -> {
                state.copy(
                    completeCount = reduce.completeCount,
                    remainingStudyCount = reduce.remainingStudyCount,
                )
            }

            is TodoContract.TodoReduce.UpdatePendingToDoCount ->
                state.copy(
                    pendingCount = reduce.pendingCount,
                )

            // ScreenType
            is TodoContract.TodoReduce.UpdateScreenType ->
                state.copy(
                    screenType = reduce.screenType,
                )

            TodoContract.TodoReduce.ResetSelectedItemList ->
                state.copy(
                    selectedItemList = listOf(),
                )
        }
    }

    private suspend fun initDataLoad() {
        getToDoInfo(
            area = ToDoConstants.TODO,
            year = 2025,
            semester = "1학기",
            sortOption = currentUiState.selectedFilterItem.id
        )
    }

    private suspend fun getToDoInfo(
        area: String,
        year: Int,
        semester: String,
        sortOption: String
    ) {
        getTodoInfoUseCase(
            area = area,
            year = year,
            semester = semester,
            sortOption = sortOption
        ).onSuccess { data ->
            Timber.tag("todayOrders").d("server viewmodel")
            updateState(
                TodoContract.TodoReduce.UpdateToDoInfo(
                    todoList = data.todoList.map { item ->
                        ToDoCardModel(
                            pieceId = item.pieceId,
                            subjectName = item.subjectName,
                            examName = item.examName,
                            studyContents = item.studyContents,
                            startPage = item.startPage,
                            finishPage = item.finishPage,
                            deadline = item.deadline,
                            remainingDays = item.remainingDays,
                            cardState = if (item.isFinished) BbangZipCardState.COMPLETE else BbangZipCardState.DEFAULT
                        )
                    },
                    pendingCount = data.pendingCount,
                    remainingStudyCount = data.remainingStudyCount,
                    completeCount = data.completeCount,
                    screenType = if (data.todoList.isEmpty()) ToDoScreenType.EMPTY else ToDoScreenType.DEFAULT
                )
            )
        }
            .onFailure { error ->
                Timber.tag("todayOrders").d(error)
            }
    }

    private suspend fun postDeletedItemList(
        selectedItemList: List<Int>
    ) {
        postDeletedItemListUseCase(
            requestPieceIdDto = RequestPieceIdDto(pieceIds = selectedItemList)
        ).onSuccess {
            Timber.tag("hide").e("삭제 성공!")
        }.onFailure {
            Timber.tag("hide").e("삭제 성공!")

        }
    }

    private suspend fun postCompleteCardId(
        pieceId: Int
    ) {
        postCompleteCardIdUseCase(
            pieceId = pieceId, requestMarkDoneDto = RequestMarkDoneDto(isFinished = true)
        ).onSuccess {
            Timber.tag("markDone").e("완료 성공!")
        }.onFailure { error ->
            Timber.tag("markDone").e(error)

        }
    }

    private suspend fun postUnCompleteCardId(
        pieceId: Int
    ) {
        postUnCompleteCardIdUseCase(
            pieceId = pieceId, requestMarkDoneDto = RequestMarkDoneDto(isFinished = false)
        ).onSuccess {
            Timber.tag("markDone").e("완료 성공!")
        }.onFailure { error ->
            Timber.tag("markDone").e(error)

        }
    }
}
