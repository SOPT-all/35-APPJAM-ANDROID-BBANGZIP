package org.android.bbangzip.presentation.ui.todo.pendingtodoadd

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.util.base.BaseContract

class TodoAddPendingContract {
    @Parcelize
    data class TodoAddPendingState(
        val todoList: List<ToDoCardModel> = emptyList(),
        val isTodoFilterBottomSheetVisible: Boolean = false,
        val selectedFilter: ToDoFilterType = ToDoFilterType.RECENT,
        val selectedItemList: List<Int> = listOf(),
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface TodoAddPendingEvent : BaseContract.Event {
        data object Initialize : TodoAddPendingEvent

        data object OnFilterIconClick : TodoAddPendingEvent

        data object OnBackIconClick : TodoAddPendingEvent

        data object OnFilterBottomSheetDismissRequest : TodoAddPendingEvent

        data object OnItemPlusBtnClick : TodoAddPendingEvent

        data class OnFilterBottomSheetItemClick(val selectedFilterItem: ToDoFilterType) : TodoAddPendingEvent

        data class OnToDoCardClick(val pieceId: Int, val cardState: BbangZipCardState) : TodoAddPendingEvent
    }

    sealed interface TodoAddPendingReduce : BaseContract.Reduce {
        data object ResetSelectedItemList : TodoAddPendingReduce

        data class UpdateSelectedItemList(
            val pieceId: Int,
        ) : TodoAddPendingReduce

        data class UpdateToDoFilterBottomSheetState(
            val isTodoFilterBottomSheetVisible: Boolean,
        ) : TodoAddPendingReduce

        data class UpdateToDoList(
            val todoList: List<ToDoCardModel>,
        ) : TodoAddPendingReduce

        data class UpdateFilterType(
            val selectedFilter: ToDoFilterType,
        ) : TodoAddPendingReduce

        data class DeleteSelectedItemList(
            val pieceId: Int,
        ) : TodoAddPendingReduce

        data class UpdateCardState(
            val pieceId: Int,
            val cardState: BbangZipCardState,
        ) : TodoAddPendingReduce
    }

    sealed interface TodoAddPendingSideEffect : BaseContract.SideEffect {
        data object NavigateToToDo : TodoAddPendingSideEffect

        data object NavigateToBack : TodoAddPendingSideEffect

        data class ShowSnackbar(
            @StringRes val message: Int,
        ) : TodoAddPendingSideEffect

        data class ShowTodoAddSnackbar(
            @StringRes val message: Int,
            val formatArg: String,
        ) : TodoAddPendingSideEffect
    }
}
