package org.android.bbangzip.presentation.ui.todo.todoadd

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.type.ToDoScreenType
import org.android.bbangzip.presentation.util.base.BaseContract

class TodoAddContract {
    @Parcelize
    data class TodoAddState(
        val todoList: List<ToDoCardModel> =
            listOf(
                ToDoCardModel(
                    pieceId = 1,
                    subjectName = "Math",
                    examName = "Algebra Exam",
                    studyContents = "Review Chapters 1-3",
                    startPage = 1,
                    finishPage = 50,
                    deadline = "2025-01-20",
                    remainingDays = 2,
                    cardState = BbangZipCardState.CHECKABLE,
                ),
                ToDoCardModel(
                    pieceId = 2,
                    subjectName = "History",
                    examName = "World War II Quiz",
                    studyContents = "Notes on WWII",
                    startPage = 5,
                    finishPage = 30,
                    deadline = "2025-01-22",
                    remainingDays = -3,
                    cardState = BbangZipCardState.CHECKABLE,
                ),
                ToDoCardModel(
                    pieceId = 3,
                    subjectName = "Science",
                    examName = "Physics Midterm",
                    studyContents = "Kinematics and Dynamics",
                    startPage = 20,
                    finishPage = 80,
                    deadline = "2025-01-25",
                    remainingDays = 7,
                    cardState = BbangZipCardState.CHECKABLE,
                ),
                ToDoCardModel(
                    pieceId = 4,
                    subjectName = "Art",
                    examName = "Painting Project",
                    studyContents = "Checkable abstract art piece",
                    startPage = 0,
                    finishPage = 0,
                    deadline = "2025-01-28",
                    remainingDays = -2,
                    cardState = BbangZipCardState.CHECKABLE,
                ),
                ToDoCardModel(
                    pieceId = 5,
                    subjectName = "History",
                    examName = "World War II Quiz",
                    studyContents = "Notes on WWII",
                    startPage = 5,
                    finishPage = 30,
                    deadline = "2025-01-22",
                    remainingDays = 4,
                    cardState = BbangZipCardState.CHECKABLE,
                ),
                ToDoCardModel(
                    pieceId = 6,
                    subjectName = "Science",
                    examName = "Physics Midterm",
                    studyContents = "Kinematics and Dynamics",
                    startPage = 20,
                    finishPage = 80,
                    deadline = "2025-01-25",
                    remainingDays = -1,
                    cardState = BbangZipCardState.CHECKABLE,
                ),
                ToDoCardModel(
                    pieceId = 7,
                    subjectName = "Art",
                    examName = "Painting Project",
                    studyContents = "Checkable abstract art piece",
                    startPage = 0,
                    finishPage = 0,
                    deadline = "2025-01-28",
                    remainingDays = 5,
                    cardState = BbangZipCardState.CHECKABLE,
                ),
                ToDoCardModel(
                    pieceId = 8,
                    subjectName = "History",
                    examName = "World War II Quiz",
                    studyContents = "Notes on WWII",
                    startPage = 5,
                    finishPage = 30,
                    deadline = "2025-01-22",
                    remainingDays = -4,
                    cardState = BbangZipCardState.CHECKABLE,
                ),
                ToDoCardModel(
                    pieceId = 9,
                    subjectName = "Science",
                    examName = "Physics Midterm",
                    studyContents = "Kinematics and Dynamics",
                    startPage = 20,
                    finishPage = 80,
                    deadline = "2025-01-25",
                    remainingDays = 3,
                    cardState = BbangZipCardState.DEFAULT,
                ),
                ToDoCardModel(
                    pieceId = 10,
                    subjectName = "Art",
                    examName = "Painting Project",
                    studyContents = "CHECKABLE abstract art piece",
                    startPage = 0,
                    finishPage = 0,
                    deadline = "2025-01-28",
                    remainingDays = -5,
                    cardState = BbangZipCardState.CHECKABLE,
                ),
            ),
        val todoFilterBottomSheetState: Boolean = false,
        val selectedFilter: ToDoFilterType = ToDoFilterType.RECENT,
        val selectedItemList: List<Int> = listOf(),
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface TodoAddEvent : BaseContract.Event {
        data object Initialize : TodoAddEvent

        data object OnFilterIconClicked : TodoAddEvent

        data object OnBackIconClicked : TodoAddEvent

        data object OnFilterBottomSheetDismissRequest : TodoAddEvent

        data object OnItemPlusButtonClicked : TodoAddEvent

        data class OnFilterBottomSheetItemClicked(val selectedFilterItem: ToDoFilterType) : TodoAddEvent

        data class OnToDoCardClicked(val pieceId: Int, val cardState: BbangZipCardState) : TodoAddEvent
    }

    sealed interface TodoAddReduce : BaseContract.Reduce {
        data object ResetSelectedItemList : TodoAddReduce

        data class UpdateToDoList(
            val todoList: List<ToDoCardModel>,
        ): TodoAddReduce

        data class UpdateSelectedItemList(
            val pieceId: Int,
        ) : TodoAddReduce

        data class UpdateToDoFilterBottomSheetState(
            val todoFilterBottomSheetState: Boolean,
        ) : TodoAddReduce

        data class UpdateFilterType(
            val selectedFilter: ToDoFilterType,
        ) : TodoAddReduce

        data class DeleteSelectedItemList(
            val pieceId: Int,
        ) : TodoAddReduce

        data class UpdateCardState(
            val pieceId: Int,
            val cardState: BbangZipCardState,
        ) : TodoAddReduce
    }

    sealed interface TodoAddSideEffect : BaseContract.SideEffect {
        data object NavigateToToDo : TodoAddSideEffect

        data object NavigateToBack : TodoAddSideEffect

        data class ShowSnackBar(val message: String) : TodoAddSideEffect

        data class ShowTodoAddSnackBar(val message: String) : TodoAddSideEffect
    }
}
