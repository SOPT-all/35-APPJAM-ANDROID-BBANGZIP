package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SubjectDetailRoute(
    padding: PaddingValues,
    viewModel: SubjectDetailViewModel = hiltViewModel(),
){
    val subjectDetailState by viewModel.uiState.collectAsStateWithLifecycle()

    SubjectDetailScreen(
        padding = padding,
        todoList = subjectDetailState.todoList,
        pieceViewType = subjectDetailState.pieceViewType,
        deletedSet = subjectDetailState.selectedItemSet,
        revertCompleteBottomSheetState = subjectDetailState.revertCompleteBottomSheetState,
        selectedItemId = subjectDetailState.selectedItemId,
        onCloseIconClicked = {viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnCloseIconClicked)},
        onTrashIconClicked = {viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnTrashIconClicked)},
        onDeleteModeCardClicked = {id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDeleteModeCardClicked(id))},
        onDefaultCardClicked = {id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDefaultCardClicked(id))},
        onCompleteCardClicked = {id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnCompleteCardClicked(id))},
        onRevertCompleteBottomSheetDismissRequest = {viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDissmissRequest)},
        onRevertCompleteBottomSheetApproveButtonClicked = {viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetApproveButtonClicked)},
        onRevertCompleteBottomSheetDismissButtonClicked = {viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDismissButtonClicked)}
    )
}