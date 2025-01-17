package org.android.bbangzip.presentation.ui.todo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoScreenType
import org.android.bbangzip.presentation.util.base.BaseContract

class TodoContract {
    @Parcelize
    data class TodoState(
        val todoList: List<ToDoCardModel> = listOf(),
        val pendingCount: Int = 0,
        val remainingStudyCount: Int = 0,
        val completeCount: Int = 0,
        val todoFilterBottomSheetState: Boolean = false,
        val todoFilterItemIndex: Int = 0,
        val selectedItemList: List<Int> = listOf(),
        val revertCompleteBottomSheetState: Boolean = false,
        val screenType: ToDoScreenType = ToDoScreenType.EMPTY
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
            val cardState: BbangZipCardState
        ) : TodoEvent

        data class FetchToDoInfo(
            val todoList: List<ToDoCardModel>,
            val pendingCount: Int,
            val remainingStudyCount: Int,
            val completeCount: Int
        ) : TodoEvent

        data class OnFilterBottomSheetItemClicked(val todoFilterItemIndex: Int) : TodoEvent

        data class OnDeleteScreenCardClicked(val pieceId: Int) : TodoEvent

    }

    sealed interface TodoReduce : BaseContract.Reduce {
        data class UpdateToDoInfo(
            val todoList: List<ToDoCardModel>,
            val pendingCount: Int,
            val remainingStudyCount: Int,
            val completeCount: Int,
        ) : TodoReduce

        data class UpdateToDoFilterBottomSheetState(val todoFilterBottomSheetState: Boolean) :
            TodoReduce

        data class UpdateRevertCompleteBottomSheetState(val revertCompleteBottomSheetState: Boolean) :
            TodoReduce

        data class UpdateToDoCount(val completeCount: Int, val remainingStudyCount: Int) :
            TodoReduce

        data class UpdateCardState(
            val pieceId: Int,
            val cardState: BbangZipCardState
        ) : TodoReduce

        data class UpdateToDoListState(
            val cardState: BbangZipCardState
        ) : TodoReduce

        data class UpdateFilterItemIndex(
            val itemIndex: Int
        ) : TodoReduce

        data class DeleteToDoListItems(
            val pieceIds: List<Int>
        ) : TodoReduce

        data class UpdatePendingToDoCount(val pendingCount: Int) : TodoReduce

        data class UpdateScreenType(val screenType: ToDoScreenType) : TodoReduce

        data class UpdateSelectedItemList(val pieceId: Int) : TodoReduce
    }

    sealed interface TodoSideEffect : BaseContract.SideEffect {
        data object NavigateToAddToDo : TodoSideEffect

        data object NavigateToAddPendingToDo : TodoSideEffect

        data class ShowSnackBar(val message: String) : TodoSideEffect
    }
}
