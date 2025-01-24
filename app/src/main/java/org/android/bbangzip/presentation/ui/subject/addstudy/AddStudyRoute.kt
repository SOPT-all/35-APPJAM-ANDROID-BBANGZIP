package org.android.bbangzip.presentation.ui.subject.addstudy

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.SplitStudyData

@Composable
fun AddStudyRoute(
    padding: PaddingValues,
    splitStudyData: SplitStudyData,
    viewModel: AddStudyViewModel = hiltViewModel(),
    popBackStack: () -> Unit = {},
    navigateSplitStudy: (AddStudyData) -> Unit = {},
    navigateSubjectDetail: (Int, String) -> Unit = { _, _ -> },
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(AddStudyContract.AddStudyEvent.Initialize(splitStudyData = splitStudyData))
    }

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest {
            when (it) {
                is AddStudyContract.AddStudySideEffect.NavigateSplitStudy -> {
                    navigateSplitStudy(it.addStudyData)
                }
                is AddStudyContract.AddStudySideEffect.PopBackStack -> popBackStack()
                is AddStudyContract.AddStudySideEffect.NavigateSubjectDetail -> navigateSubjectDetail(it.subjectId, it.subjectName)
            }
        }
    }

    val addStudyState by viewModel.uiState.collectAsStateWithLifecycle()

    if (addStudyState.isSuccess) {
        AddStudyScreen(
            padding = padding,
            pieceNumber = addStudyState.pieceNumber,
            subjectTitle = addStudyState.subjectName,
            examDate = addStudyState.examDate,
            examName = addStudyState.examName,
            studyContent = addStudyState.studyContent ?: "",
            startPage = addStudyState.startPage ?: "",
            startGuideline = addStudyState.startPageGuideline,
            endPage = addStudyState.endPage ?: "",
            endGuideline = addStudyState.endPageGuideline,
            addStudyViewType = addStudyState.addStudyViewType,
            selectedDate = addStudyState.selectedDate,
            datePickerBottomSheetState = addStudyState.datePickerBottomSheetState,
            piecePickerBottomSheetState = addStudyState.piecePickerBottomSheetState,
            studyContentTextFieldState = addStudyState.studyContentTextFieldState,
            startPageTextFieldState = addStudyState.startPageTextFieldState,
            endPageTextFieldState = addStudyState.endPageTextFieldState,
            isSplitBtnEnable = addStudyState.buttonSplitEnabled,
            isButtonEnable = addStudyState.buttonEnabled,
            onChangeSelectedDate = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeSelectedDate(it)) },
            onChangeStudyContent = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStudyContent(it)) },
            onChangeStudyContentFocused = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStudyContentFocused(it)) },
            onChangeStartPage = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStartPage(it)) },
            onChangeStartPageFocused = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStartPageFocused(it)) },
            onChangeEndPage = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeEndPage(it)) },
            onChangeEndPageFocused = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeEndPageFocused(it)) },
            onClickSplitBtn = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickSplitBtn) },
            onClickDatePicker = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickDatePicker) },
            onClickPieceNumber = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickPieceNumber(it)) },
            onClickCancleIcon = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickCancleIcon) },
            onClickConfirmDateBtn = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickConfirmDateBtn) },
            onClickAgainSplitBtn = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickAgainSplitBtn(it)) },
            onClickAddStudyBtn = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickAddStudyBtn) },
        )
    }
}
