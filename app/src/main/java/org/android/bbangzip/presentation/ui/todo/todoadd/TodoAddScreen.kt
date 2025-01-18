package org.android.bbangzip.presentation.ui.todo.todoadd

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.type.ToDoFilterType

@Composable
fun TodoAddScreen(
    todoAddState : TodoAddContract.TodoAddState,
    modifier: Modifier = Modifier,
    onBackIconClicked: () -> Unit,
    onFilterBottomSheetDismissRequest: () -> Unit,
    nFilterIconClicked: () -> Unit,
    onFilterBottomSheetItemClicked: (ToDoFilterType) -> Unit,
    onItemPlusButtonClicked: () -> Unit,
    onToDoCardClicked: (Int, BbangZipCardState) -> Unit = { _, _ -> },
    ) {

}