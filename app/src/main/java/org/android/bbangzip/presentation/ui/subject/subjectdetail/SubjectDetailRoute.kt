package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SubjectDetailRoute(
    padding: PaddingValues,
    subjectId: Int,
    navigateToBack: () -> Unit,
    navigateToModifyMotivation: () -> Unit,
    navigateToModifySubjectName: (String) -> Unit,
    viewModel: SubjectDetailViewModel = hiltViewModel(),
) {
    val subjectDetailState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.Initialize(subjectId))
    }

    SubjectDetailScreen(
        padding = padding,
        todoList = subjectDetailState.todoList,
        pieceViewType = subjectDetailState.pieceViewType,
        deletedSet = subjectDetailState.selectedItemSet,
        revertCompleteBottomSheetState = subjectDetailState.revertCompleteBottomSheetState,
        selectedItemId = subjectDetailState.selectedItemId,
        onClickCancleBtn = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDeleteButtonClicked) },
        onCloseIconClicked = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnCloseIconClicked) },
        onTrashIconClicked = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnTrashIconClicked) },
        onDeleteModeCardClicked = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDeleteModeCardClicked(id)) },
        onDefaultCardClicked = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDefaultCardClicked(id)) },
        onCompleteCardClicked = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnCompleteCardClicked(id)) },
        onRevertCompleteBottomSheetDismissRequest = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDissmissRequest) },
        onRevertCompleteBottomSheetApproveButtonClicked = { pieceId -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetApproveButtonClicked(pieceId = pieceId)) },
        onRevertCompleteBottomSheetDismissButtonClicked = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDismissButtonClicked) },
    )
}
