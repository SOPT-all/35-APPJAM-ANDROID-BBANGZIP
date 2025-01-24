package org.android.bbangzip.presentation.ui.subject.splitstudy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.SplitStudyData

@Composable
fun SplitStudyRoute(
    viewModel: SplitStudyViewModel = hiltViewModel(),
    addStudyData: AddStudyData,
    onBackPress: () -> Unit = {},
    navigateAddStudy: (SplitStudyData) -> Unit = {},
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(SplitStudyContract.SplitStudyEvent.Initialize(addStudyData = addStudyData))
    }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest { effect ->
            when (effect) {
                is SplitStudyContract.SplitStudySideEffect.NavigateAddStudy -> {
                    navigateAddStudy(effect.splitStudyData)
                }
                is SplitStudyContract.SplitStudySideEffect.NavigateBack -> {
                    onBackPress()
                }
            }
        }
    }

    val splitStudyState by viewModel.uiState.collectAsStateWithLifecycle()

    if (splitStudyState.isSuccess) {
        SplitStudyScreen(
            subjectId = splitStudyState.subjectId,
            pieceNumber = splitStudyState.pieceNumber,
            subjectName = splitStudyState.subjectName,
            examDate = splitStudyState.examDate,
            examName = splitStudyState.examName,
            studyContent = splitStudyState.studyContent,
            startPage = splitStudyState.startPage,
            endPage = splitStudyState.endPage,
            selectedDate = splitStudyState.selectedDate,
            selectedIndex = splitStudyState.selectedPieceIndex,
            datePickerBottomSheetState = splitStudyState.datePickerBottomSheetState,
            startPageList = splitStudyState.startPageList,
            startPageTextFieldStateList = splitStudyState.startPageTextFieldStateList,
            startPageFocusedStateList = splitStudyState.startPageFocusedStateList,
            startPageGuidelineList = splitStudyState.startPageGuidelineList,
            endPageList = splitStudyState.endPageList,
            endPageTextFieldStateList = splitStudyState.endPageTextFieldStateList,
            endPageFocusedStateList = splitStudyState.endPageFocusedStateList,
            endPageGuidelineList = splitStudyState.endPageGuidelineList,
            seletedDateList = splitStudyState.dateList,
            isSaveEnable = splitStudyState.isSaveEnable,
            onBackBtnClick = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnClickBackIcon(it))
            },
            onChangeStartPage = { index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnChangeStartPage(index, value))
            },
            onChangeEndPage = { index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnChangeEndPage(index, value))
            },
            onChangeStartPageFocused = { index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnChangeStartPageFocused(index, value))
            },
            onChangeEndPageFocused = { index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnChangeEndPageFocused(index, value))
            },
            onClickDatePicker = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnClickDatePicker(it))
            },
            onCloseBottomSheet = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnCloseBottomSheet)
            },
            onClickConfirmDateBtn = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnClickConfirmDateBtn)
            },
            onChangeSelectedDate = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnChangeSelectedDate(it))
            },
            onClickSaveButton = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnClickSaveBtn(it))
            },
        )
    }
}
