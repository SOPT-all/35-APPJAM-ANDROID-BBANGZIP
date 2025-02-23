package org.android.bbangzip.presentation.ui.todo.todoadd

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.ToDoFilterType
import org.android.bbangzip.presentation.util.base.BaseContract

class TodoAddContract {
    @Parcelize
    data class TodoAddState(
        val todoList: List<ToDoCardModel> = emptyList(),
        val isTodoFilterBottomSheetVisible: Boolean = false,
        val selectedFilter: ToDoFilterType = ToDoFilterType.RECENT,
        val selectedItemList: List<Int> = listOf(),
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface TodoAddEvent : BaseContract.Event {
        data object Initialize : TodoAddEvent

        data object OnFilterIconClick : TodoAddEvent

        data object OnBackIconClick : TodoAddEvent

        data object OnFilterBottomSheetDismissRequest : TodoAddEvent

        data object OnItemPlusBtnClick : TodoAddEvent

        data class OnFilterBottomSheetItemClick(val selectedFilterItem: ToDoFilterType) : TodoAddEvent

        data class OnToDoCardClick(val pieceId: Int, val cardState: BbangZipCardState) : TodoAddEvent
    }

    sealed interface TodoAddReduce : BaseContract.Reduce {
        data object ResetSelectedItemList : TodoAddReduce

        data class UpdateToDoList(
            val todoList: List<ToDoCardModel>,
        ) : TodoAddReduce

        data class UpdateSelectedItemList(
            val pieceId: Int,
        ) : TodoAddReduce

        data class UpdateToDoFilterBottomSheetState(
            val isTodoFilterBottomSheetVisible: Boolean,
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

        data class ShowSnackBar(
            @StringRes val message: Int,
        ) : TodoAddSideEffect

        data class ShowTodoAddSnackBar(
            @StringRes val message: Int,
            val formatArg: String = "",
        ) : TodoAddSideEffect
    }
}
