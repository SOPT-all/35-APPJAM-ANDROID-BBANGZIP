package org.android.bbangzip.presentation.ui.todo.todoadd

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.type.ToDoScreenType
import org.android.bbangzip.presentation.ui.todo.TodoContract.TodoEvent
import org.android.bbangzip.presentation.ui.todo.TodoContract.TodoReduce
import org.android.bbangzip.presentation.util.base.BaseContract

class TodoContract {
    @Parcelize
    data class TodoAddState(
        val todoList: List<ToDoCardModel> = listOf(),
        val todoFilterBottomSheetState: Boolean = false,
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

        data class UpdateSelectedItemList(
            val pieceId: Int
        ) : TodoAddReduce

        data class UpdateToDoFilterBottomSheetState(
            val todoFilterBottomSheetState: Boolean
        ) : TodoAddReduce

        data class UpdateFilterType(
            val selectedFilter: ToDoFilterType
        ) : TodoAddReduce

        data class UpdateCardState(
            val pieceId: Int,
            val cardState: BbangZipCardState
        ) : TodoAddReduce
    }

    sealed interface TodoAddSideEffect : BaseContract.SideEffect {
        data object NavigateToToDo : TodoAddSideEffect

        data object NavigateToBack : TodoAddSideEffect

        data class ShowSnackBar(val message: String) : TodoAddSideEffect
    }
}