package org.android.bbangzip.presentation.ui.todo

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.android.bbangzip.presentation.component.card.BbangZipCardState

@Composable
fun TodoScreen(
    todoState: TodoContract.TodoState,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onAddPendingStudyButtonClicked: () -> Unit = {},
    onAddStudyButtonClicked: () -> Unit = {},
    onRevertCompleteBottomSheetDismissButtonClicked: () -> Unit = {},
    onRevertCompleteBottomSheetApproveButtonClicked: (Int, BbangZipCardState) -> Unit = { _, _ -> },
    onRevertCompleteBottomSheetDismissRequest: () -> Unit = {},
    onFilterIconClicked: () -> Unit = {},
    onFilterBottomSheetItemClicked: (Int) -> Unit = {},
    onFilterBottomSheetDismissRequest: () -> Unit = {},
    onDeleteIconClicked: () -> Unit = {},
    onCloseIconClicked: () -> Unit = {},
    onItemDeleteButtonClicked: () -> Unit = {},
    onDeleteScreenCardClicked: (Int) -> Unit = {}
) {
    Text("todo 탭")
}
