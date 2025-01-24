package org.android.bbangzip.presentation.ui.subject.subjectdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.model.SplitStudyData
import org.android.bbangzip.ui.theme.BbangZipTheme

@Composable
fun SubjectDetailRoute(
    padding: PaddingValues,
    subjectId: Int,
    subjectName: String,
    popBackStack: () -> Unit,
    navigateToSubject: () -> Unit = {},
    navigateToModifyMotivation: (Int, String) -> Unit,
    navigateToModifySubjectName: (Int, String) -> Unit,
    navigateToAddStudy: (SplitStudyData) -> Unit,
    viewModel: SubjectDetailViewModel = hiltViewModel(),
) {
    val subjectDetailState by viewModel.uiState.collectAsStateWithLifecycle()
    val success by viewModel.success.collectAsStateWithLifecycle(initialValue = false)
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
                    navigateToAddStudy(effect.splitStudyData)
                }

                is SubjectDetailContract.SubjectDetailSideEffect.ShowDeleteSuccessSnackBar -> {
                }

                is SubjectDetailContract.SubjectDetailSideEffect.NavigateToModifyMotivation -> {
                    navigateToModifyMotivation(effect.subjectId, effect.subjectName)
                }

                is SubjectDetailContract.SubjectDetailSideEffect.NavigateToModifySubjectName -> {
                    navigateToModifySubjectName(effect.subjectId, effect.subjectName)
                }

                is SubjectDetailContract.SubjectDetailSideEffect.PopBackStack -> {
                    navigateToSubject()
                }
            }
        }
    }

    when (success) {
        true ->
            SubjectDetailScreen(
                padding = padding,
                state = subjectDetailState,
                isMenuOpen = subjectDetailState.isMenuOpen,
                todoList = subjectDetailState.todoList,
                pieceViewType = subjectDetailState.pieceViewType,
                deletedSet = subjectDetailState.selectedItemSet,
                revertCompleteBottomSheetState = subjectDetailState.revertCompleteBottomSheetState,
                selectedItemId = subjectDetailState.selectedItemId,
                subjectId = subjectDetailState.subjectId,
                subjectName = subjectDetailState.subjectName,
                motivationMessage = if(subjectDetailState.motivationMessage == "") "사장님의 각오 한마디를 작성해보세요" else subjectDetailState.motivationMessage,
                examDDay = subjectDetailState.examDday,
                examDate = subjectDetailState.examDate,
                examName = subjectDetailState.examName,
                onClickDeleteBtn = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDeleteButtonClicked) },
                onClickTab = { index -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnClickTab(index)) },
                onCloseIconClicked = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnCloseIconClicked) },
                onTrashIconClicked = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnTrashIconClicked) },
                onDeleteModeCardClicked = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDeleteModeCardClicked(id)) },
                onDefaultCardClicked = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDefaultCardClicked(id)) },
                onCompleteCardClicked = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnCompleteCardClicked(id)) },
                onClickEnrollMotivationMessage = { id, name -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnClickEnrollMotivateMessage(id, name)) },
                onClickModifySubjectName = { id, name -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnClickModifySubjectName(id, name)) },
                onClickKebabMenu = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnClickKebabMenu) },
                onClickAddStudy = { splitStudyData -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnPlusIconClicked(splitStudyData)) },
                onRevertCompleteBottomSheetDismissRequest = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDissmissRequest) },
                onRevertCompleteBottomSheetApproveButtonClicked = { pieceId -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetApproveButtonClicked(pieceId = pieceId)) },
                onRevertCompleteBottomSheetDismissButtonClicked = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDismissButtonClicked) },
                onClickBadgeCloseBtn = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnClickGetBadgeBottomSheetCloseBtn) },
                popBackStack = { popBackStack() },
            )

        false ->
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(color = BbangZipTheme.colors.staticWhite_FFFFFF),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = BbangZipTheme.colors.backgroundAccent_FFDAA0)
            }
    }
}
