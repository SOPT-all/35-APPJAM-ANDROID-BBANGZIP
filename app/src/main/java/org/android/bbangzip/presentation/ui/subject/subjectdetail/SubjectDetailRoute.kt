package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SubjectDetailRoute(
    padding: PaddingValues,
    subjectId: Int,
    subjectName: String,
    navigateToBack: () -> Unit,
    navigateToModifyMotivation: (Int, String) -> Unit,
    navigateToModifySubjectName: (Int, String) -> Unit,
    viewModel: SubjectDetailViewModel = hiltViewModel(),
) {
    val subjectDetailState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.Initialize(subjectId, subjectName))
    }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is SubjectDetailContract.SubjectDetailSideEffect.NavigateToAddSubject -> {
//                    navigateToModifyMotivation()
                }
                is SubjectDetailContract.SubjectDetailSideEffect.NavigateToAddStudy -> {
//                    navigateToModifySubjectName("")
                }
                is SubjectDetailContract.SubjectDetailSideEffect.ShowDeleteSuccessSnackBar -> {
                }

                is SubjectDetailContract.SubjectDetailSideEffect.NavigateToModifyMotivation -> {
                    navigateToModifyMotivation(effect.subjectId, effect.subjectName)
                }

                is SubjectDetailContract.SubjectDetailSideEffect.NavigateToModifySubjectName -> {
                    navigateToModifySubjectName(effect.subjectId, effect.subjectName)
                }
            }
        }
    }

    SubjectDetailScreen(
        padding = padding,
        isMenuOpen = subjectDetailState.isMenuOpen,
        todoList = subjectDetailState.todoList,
        pieceViewType = subjectDetailState.pieceViewType,
        deletedSet = subjectDetailState.selectedItemSet,
        revertCompleteBottomSheetState = subjectDetailState.revertCompleteBottomSheetState,
        selectedItemId = subjectDetailState.selectedItemId,
        subjectId = subjectDetailState.subjectId,
        subjectName = subjectDetailState.subjectName,
        motivationMessage = subjectDetailState.motivationMessage,
        examDDay = subjectDetailState.examDday,
        examDate = subjectDetailState.examDate,
        onCloseIconClicked = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnCloseIconClicked) },
        onTrashIconClicked = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnTrashIconClicked) },
        onDeleteModeCardClicked = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDeleteModeCardClicked(id)) },
        onDefaultCardClicked = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDefaultCardClicked(id)) },
        onCompleteCardClicked = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnCompleteCardClicked(id)) },
        onClickEnrollMotivationMessage = { id, name -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnClickEnrollMotivateMessage(id, name)) },
        onClickModifySubjectName = { id, name -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnClickModifySubjectName(id, name)) },
        onClickKebabMenu = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnClickKebabMenu) },
        onRevertCompleteBottomSheetDismissRequest = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDissmissRequest) },
        onRevertCompleteBottomSheetApproveButtonClicked = { pieceId -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetApproveButtonClicked(pieceId = pieceId)) },
        onRevertCompleteBottomSheetDismissButtonClicked = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDismissButtonClicked) },
    )
}
