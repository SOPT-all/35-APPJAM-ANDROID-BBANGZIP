package org.android.bbangzip.presentation.ui.subject.addstudy

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.android.bbangzip.presentation.model.AddStudyData

@Composable
fun AddStudyRoute(
    padding: PaddingValues,
    viewModel: AddStudyViewModel = hiltViewModel(),
    navigateSplitStudy: (AddStudyData) -> Unit = {}
){
    val addStudyState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.uiSideEffect) {
        viewModel.uiSideEffect.collectLatest {
            when (it) {
                is AddStudyContract.AddStudySideEffect.NavigateSplitStudy -> {
                    navigateSplitStudy(it.addStudyData)
                }
            }
        }
    }

    AddStudyScreen(
        padding = padding,
        subjectTitle = addStudyState.subjectName,
        examDate = addStudyState.examDate,
        studyContent = addStudyState.studyContent?: "",
        startPage = addStudyState.startPage?: "",
        endPage = addStudyState.endPage?: "",
        selectedDate = addStudyState.selectedDate,
        datePickerBottomSheetState = addStudyState.datePickerBottomSheetState,
        piecePickerBottomSheetState = addStudyState.piecePickerBottomSheetState,
        studyContentTextFieldState = addStudyState.studyContentTextFieldState,
        isSplitBtnEnable = addStudyState.buttonSplitEnabled,
        isButtonEnable = addStudyState.buttonEnabled,
        onChangeSelectedDate = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeSelectedDate(it))},
        onChangeStudyContent = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStudyContent(it)) },
        onChangeStudyContentFocused = {viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStudyContentFocused(it))},
        onChangeStartPage = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStartPage(it)) },
        onChangeStartPageFocused = {viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStartPageFocused(it))},
        onChangeEndPage = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeEndPage(it)) },
        onChangeEndPageFocused = {viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeEndPageFocused(it))},
        onClickSplitBtn = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickSplitBtn) },
        onClickDatePicker = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickDatePicker) },
        onClickPieceNumber = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickPieceNumber(it)) },
        onClickCancleIcon = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickCancleIcon) },
        onClickConfirmDateBtn = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickConfirmDateBtn) },
    )
}