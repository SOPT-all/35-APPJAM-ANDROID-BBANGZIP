package org.android.bbangzip.presentation.ui.todo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.Badge
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.type.ToDoScreenType
import org.android.bbangzip.presentation.ui.subject.subjectdetail.SubjectDetailContract.SubjectDetailEvent
import org.android.bbangzip.presentation.util.base.BaseContract

class TodoContract {
    @Parcelize
    data class TodoState(
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
                    cardState = BbangZipCardState.DEFAULT,
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
                    cardState = BbangZipCardState.COMPLETE,
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
                    cardState = BbangZipCardState.DEFAULT,
                ),
                ToDoCardModel(
                    pieceId = 4,
                    subjectName = "Art",
                    examName = "Painting Project",
                    studyContents = "Complete abstract art piece",
                    startPage = 0,
                    finishPage = 0,
                    deadline = "2025-01-28",
                    remainingDays = -2,
                    cardState = BbangZipCardState.COMPLETE,
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
                    cardState = BbangZipCardState.DEFAULT,
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
                    cardState = BbangZipCardState.COMPLETE,
                ),
                ToDoCardModel(
                    pieceId = 7,
                    subjectName = "Art",
                    examName = "Painting Project",
                    studyContents = "Complete abstract art piece",
                    startPage = 0,
                    finishPage = 0,
                    deadline = "2025-01-28",
                    remainingDays = 5,
                    cardState = BbangZipCardState.DEFAULT,
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
                    cardState = BbangZipCardState.COMPLETE,
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
                    studyContents = "Complete abstract art piece",
                    startPage = 0,
                    finishPage = 0,
                    deadline = "2025-01-28",
                    remainingDays = -5,
                    cardState = BbangZipCardState.COMPLETE,
                ),
            ),
        val pendingCount: Int = 1,
        val remainingStudyCount: Int = 5,
        val completeCount: Int = 5,
        val todoFilterBottomSheetState: Boolean = false,
        val selectedFilterItem: ToDoFilterType = ToDoFilterType.RECENT,
        val selectedItemList: List<Int> = listOf(),
        val revertCompleteBottomSheetState: Boolean = false,
        val screenType: ToDoScreenType = ToDoScreenType.DEFAULT,
        val badgeList: List<Badge> = emptyList(),
        val getBadgeBottomSheetState: Boolean = false
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface TodoEvent : BaseContract.Event {
        data object Initialize : TodoEvent

        data object OnAddPendingStudyButtonClicked : TodoEvent

        data object OnAddStudyButtonClicked : TodoEvent

        data object OnRevertCompleteBottomSheetDismissButtonClicked : TodoEvent

        data object OnFilterBottomSheetDismissRequest : TodoEvent

        data object OnRevertCompleteBottomSheetDismissRequest : TodoEvent

        data object OnFilterIconClicked : TodoEvent

        data object OnDeleteIconClicked : TodoEvent

        data object OnCloseIconClicked : TodoEvent

        data object OnItemDeleteButtonClicked : TodoEvent

        data class OnRevertCompleteBottomSheetApproveButtonClicked(
            val pieceId: Int,
        ) : TodoEvent

        data class FetchToDoInfo(
            val todoList: List<ToDoCardModel>,
            val pendingCount: Int,
            val remainingStudyCount: Int,
            val completeCount: Int,
            val screenType: ToDoScreenType,
        ) : TodoEvent

        data class OnFilterBottomSheetItemClicked(
            val selectedFilterItem: ToDoFilterType,
        ) : TodoEvent

        data class OnDeleteScreenCardClicked(
            val pieceId: Int,
            val cardState: BbangZipCardState,
        ) :
            TodoEvent

        data class OnDefaultScreenCardClicked(
            val pieceId: Int,
            val cardState: BbangZipCardState,
        ) : TodoEvent

        data object OnClickGetBadgeBottomSheetCloseBtn: TodoEvent
    }

    sealed interface TodoReduce : BaseContract.Reduce {
        data class UpdateToDoInfo(
            val todoList: List<ToDoCardModel>,
            val pendingCount: Int,
            val remainingStudyCount: Int,
            val completeCount: Int,
            val screenType: ToDoScreenType,
        ) : TodoReduce

        data object DeleteToDoListItems : TodoReduce

        data class UpdateToDoFilterBottomSheetState(val todoFilterBottomSheetState: Boolean) :
            TodoReduce

        data class UpdateRevertCompleteBottomSheetState(val revertCompleteBottomSheetState: Boolean) :
            TodoReduce

        data class UpdateToDoCount(val completeCount: Int, val remainingStudyCount: Int) :
            TodoReduce

        data class UpdateCardState(
            val pieceId: Int,
            val cardState: BbangZipCardState,
        ) : TodoReduce

        data class UpdateToDoListCardState(
            val previousCardState: BbangZipCardState,
            val nextCardState: BbangZipCardState,
        ) : TodoReduce

        data class UpdateFilterType(
            val selectedFilter: ToDoFilterType,
        ) : TodoReduce

        data class UpdatePendingToDoCount(val pendingCount: Int) : TodoReduce

        data class UpdateScreenType(val screenType: ToDoScreenType) : TodoReduce

        data class UpdateSelectedItemList(val pieceId: Int) : TodoReduce

        data class DeleteSelectedItemList(val pieceId: Int) : TodoReduce

        data object ResetSelectedItemList : TodoReduce

        data class UpdateGetBadgeList(val badgeList: List<Badge>): TodoReduce

        data class UpdateGetBadgeBottomSheetState(val getBadgeBottomSheetState: Boolean) : TodoReduce
    }

    sealed interface TodoSideEffect : BaseContract.SideEffect {
        data object NavigateToAddToDo : TodoSideEffect

        data object NavigateToAddPendingToDo : TodoSideEffect

        data class ShowSnackBar(val message: String) : TodoSideEffect
    }
}
