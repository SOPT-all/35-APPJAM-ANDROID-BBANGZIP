package org.android.bbangzip.presentation.ui.todo

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.Badge
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.type.ToDoScreenType
import org.android.bbangzip.presentation.util.base.BaseContract

class TodoContract {
    @Parcelize
    data class TodoState(
        val todoList: List<ToDoCardModel> = emptyList(),
        val pendingCount: Int = 1,
        val remainingStudyCount: Int = 5,
        val completeCount: Int = 5,
        val isTodoFilterBottomSheetVisible: Boolean = false,
        val selectedFilterItem: ToDoFilterType = ToDoFilterType.RECENT,
        val selectedItemList: List<Int> = listOf(),
        val isRevertCompleteBottomSheetVisible: Boolean = false,
        val screenType: ToDoScreenType = ToDoScreenType.DEFAULT,
        val badgeList: List<Badge> = emptyList(),
        val isGetBadgeBottomSheetVisible: Boolean = false,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface TodoEvent : BaseContract.Event {
        data object Initialize : TodoEvent

        data object OnAddPendingStudyBtnClick : TodoEvent

        data object OnAddStudyBtnClick : TodoEvent

        data object OnRevertCompleteBottomSheetDismissBtnClick : TodoEvent

        data object OnFilterBottomSheetDismissRequest : TodoEvent

        data object OnRevertCompleteBottomSheetDismissRequest : TodoEvent

        data object OnFilterIconClick : TodoEvent

        data object OnDeleteIconClick : TodoEvent

        data object OnCloseIconClick : TodoEvent

        data object OnItemDeleteBtnClick : TodoEvent

        data class OnRevertCompleteBottomSheetApproveBtnClick(
            val pieceId: Int,
        ) : TodoEvent

        data class FetchToDoInfo(
            val todoList: List<ToDoCardModel>,
            val pendingCount: Int,
            val remainingStudyCount: Int,
            val completeCount: Int,
            val screenType: ToDoScreenType,
        ) : TodoEvent

        data class OnFilterBottomSheetItemClick(
            val selectedFilterItem: ToDoFilterType,
        ) : TodoEvent

        data class OnDeleteScreenCardClick(
            val pieceId: Int,
            val cardState: BbangZipCardState,
        ) :
            TodoEvent

        data class OnDefaultScreenCardClick(
            val pieceId: Int,
            val cardState: BbangZipCardState,
        ) : TodoEvent

        data object OnGetBadgeBottomSheetCloseBtnClick : TodoEvent
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

        data class UpdateToDoFilterBottomSheetState(val isTodoFilterBottomSheetVisible: Boolean) :
            TodoReduce

        data class UpdateRevertCompleteBottomSheetState(val isRevertCompleteBottomSheetVisible: Boolean) :
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

        data class UpdateGetBadgeList(val badgeList: List<Badge>) : TodoReduce

        data class UpdateGetBadgeBottomSheetState(val isGetBadgeBottomSheetVisible: Boolean) : TodoReduce
    }

    sealed interface TodoSideEffect : BaseContract.SideEffect {
        data object NavigateToAddToDo : TodoSideEffect

        data object NavigateToAddPendingToDo : TodoSideEffect

        data class ShowSnackbar(
            @StringRes val message: Int,
        ) : TodoSideEffect

        data class ShowFormattedSnackbar(
            @StringRes val message: Int,
            val formatArg: String,
        ) : TodoSideEffect
    }
}
