package org.android.bbangzip.presentation.ui.subject.addstudy

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import timber.log.Timber

@Composable
fun AddStudyRoute(
    padding: PaddingValues,
    viewModel: AddStudyViewModel = hiltViewModel()
){
    val addStudyState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(addStudyState.selectedDate) {
        Timber.d("[selectedDate] -> ${addStudyState.selectedDate}")
    }

    AddStudyScreen(
        padding = padding,
        examDate = addStudyState.examDate,
        studyContent = addStudyState.studyContent?: "",
        startPage = addStudyState.startPage?: "",
        endPage = addStudyState.endPage?: "",
        selectedDate = addStudyState.selectedDate,
        datePickerBottomSheetState = addStudyState.datePickerBottomSheetState,
        piecePickerBottomSheetState = addStudyState.piecePickerBottomSheetState,
        studyContentTextFieldState = addStudyState.studyContentTextFieldState,
        onChangeSelectedDate = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeSelectedDate(it))},
        onChangeStudyContent = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStudyContent(it)) },
        onChangeStudyContentFocused = {viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStudyContentFocused(it))},
        onChangeStartPage = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStartPage(it)) },
        onChangeStartPageFocused = {viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeStartPageFocused(it))},
        onChangeEndPage = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeEndPage(it)) },
        onChangeEndPageFocused = {viewModel.setEvent(AddStudyContract.AddStudyEvent.OnChangeEndPageFocused(it))},
        onClickDatePicker = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickDatePicker) },
        onClickPieceNumber = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickPieceNumber) },
        onClickConfirmDateBtn = { viewModel.setEvent(AddStudyContract.AddStudyEvent.OnClickConfirmDateBtn) },
    )
}