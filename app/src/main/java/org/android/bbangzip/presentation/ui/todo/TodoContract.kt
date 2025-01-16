package org.android.bbangzip.presentation.ui.todo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.util.base.BaseContract

class TodoContract {
    @Parcelize
    data class TodoState(
        val todoList: List<ToDoCardModel>,
        val pendingCount: Int,
        val remainingStudyCount: Int,
        val completeCount: Int,
        val todoFilterBottomSheetState: Boolean,
        val todoFilterItemIndex: Int,
        val revertCompleteBottomSheetState: Boolean,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface TodoEvent : BaseContract.Event {
        data object Initialize : TodoEvent

        data object OnPendingStudyButtonClicked : TodoEvent

        data object OnAddStudyButtonClicked : TodoEvent

        data object OnFilterDismissRequest : TodoEvent

        data object OnRevertCompleteDismissButtonClicked : TodoEvent

        data object OnRevertCompleteDismissRequest : TodoEvent

        data object OnFilterIconClicked : TodoEvent

        data object OnDeleteIconClicked : TodoEvent

        data object OnCloseIconClicked : TodoEvent

        data class OnItemDeleteButtonClicked(val pieceIds: List<Long>) : TodoEvent

        data class FetchToDoInfo(
            val year: Int,
            val semester: String,
            val sortOption: String = ToDoFilterType.RECENT.id,
        ) : TodoEvent

        data class OnFilterItemClicked(val todoFilterItemIndex: Int) : TodoEvent

        data class OnRevertCompleteApproveButtonClicked(val pieceIds: List<Long>) : TodoEvent
    }

    sealed interface TodoReduce : BaseContract.Reduce {
        data class UpdateToDoInfo(
            val todoList: List<ToDoCardModel>,
            val pendingCount: Int,
            val remainingStudyCount: Int,
            val completeCount: Int,
        ) : TodoReduce

        data class UpdateToDoFilterBottomSheetState(val todoFilterBottomSheetState: Boolean)

        data class UpdateRevertCompleteBottomSheetState(val revertCompleteBottomSheetState: Boolean)

        data class UpdateToDoCount(val completeCount: Int, val remainingStudyCount: Int)

        data class UpdateCardState(val cardState: BbangZipCardState)
    }

    sealed interface TodoSideEffect : BaseContract.SideEffect {
        data object NavigateToAddToDo : TodoSideEffect

        data object NavigateToAddPendingToDo : TodoSideEffect

        data class ShowSnackBar(val message: String) : TodoSideEffect
    }
}
