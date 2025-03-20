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
    navigateToBack: () -> Unit,
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
                is SubjectDetailContract.SubjectDetailSideEffect.NavigateToAddStudy -> {
                    navigateToAddStudy(effect.splitStudyData)
                }

                is SubjectDetailContract.SubjectDetailSideEffect.ShowSnackBar -> {
                }

                is SubjectDetailContract.SubjectDetailSideEffect.NavigateToModifyMotivation -> {
                    navigateToModifyMotivation(effect.subjectId, effect.subjectName)
                }

                is SubjectDetailContract.SubjectDetailSideEffect.NavigateToModifySubjectName -> {
                    navigateToModifySubjectName(effect.subjectId, effect.subjectName)
                }

                is SubjectDetailContract.SubjectDetailSideEffect.NavigateToBack -> {
                    navigateToBack()
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
                deletedSet = subjectDetailState.selectedPieceSet,
                revertCompleteBottomSheetState = subjectDetailState.isRevertCompleteBottomSheetVisible,
                selectedItemId = subjectDetailState.selectedPieceId,
                subjectId = subjectDetailState.subjectId,
                subjectName = subjectDetailState.subjectName,
                motivationMessage = if (subjectDetailState.motivationMessage == "") "사장님의 각오 한마디를 작성해보세요" else subjectDetailState.motivationMessage,
                examDDay = subjectDetailState.examDDay,
                examDate = subjectDetailState.examDate,
                examName = subjectDetailState.examName,
                onDeleteBtnClick = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDeleteBtnClick) },
                onTabClick = { index -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnTabClick(index)) },
                onCloseIconClick = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnCloseIconClick) },
                onTrashIconClick = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnTrashIconClick) },
                onDeleteModePieceCardClick = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDeleteModePieceCardClick(id)) },
                onDefaultModePieceCardClick = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnDefaultModePieceCardClick(id)) },
                onCompleteModePieceCardClick = { id -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnCompleteModePieceCardClick(id)) },
                onEnrollMotivationMessageClick = { id, name -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnEnrollMotivateMessageClick(id, name)) },
                onModifySubjectNameClick = { id, name -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnModifySubjectNameClick(id, name)) },
                onKebabIconClick = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnKebabIconClick) },
                onAddStudyCardClick = { splitStudyData -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnAddStudyCardClick(splitStudyData)) },
                onAddStudyBtnClick = { splitStudyData -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnAddStudyCardClick(splitStudyData)) },
                onPlusIconClick = { splitStudyData -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnPlusIconClick(splitStudyData)) },
                onRevertCompleteBottomSheetDismissRequest = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDismissRequest) },
                onRevertCompleteBottomSheetApproveBtnClick = { pieceId -> viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetApproveBtnClick(pieceId = pieceId)) },
                onRevertCompleteBottomSheetDismissBtnClick = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDismissBtnClick) },
                onGetBadgeBottomSheetCloseBtnClick = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnGetBadgeBottomSheetCloseBtnClick) },
                onMenuDismissRequest = { viewModel.setEvent(SubjectDetailContract.SubjectDetailEvent.OnMenuDismissRequest) },
                navigateToBack = { navigateToBack() },
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
