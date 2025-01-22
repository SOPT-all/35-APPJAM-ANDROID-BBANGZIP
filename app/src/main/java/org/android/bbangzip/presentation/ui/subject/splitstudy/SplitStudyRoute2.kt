package org.android.bbangzip.presentation.ui.subject.splitstudy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.android.bbangzip.presentation.model.AddStudyData
import timber.log.Timber

@Composable
fun SplitStudyRoute2(
    viewModel: SplitStudyViewModel = hiltViewModel(),
    addStudyData: AddStudyData
){
    LaunchedEffect(Unit){
        viewModel.setEvent(SplitStudyContract.SplitStudyEvent.Initialize(addStudyData = addStudyData))
    }

    val splitStudyState by viewModel.uiState.collectAsStateWithLifecycle()

    if (splitStudyState.isSuccess) {
        SplitStudyScreen(
            pieceNumber = splitStudyState.pieceNumber,
            subjectName = splitStudyState.subjectName,
            startPage = splitStudyState.startPage,
            endPage = splitStudyState.endPage,
            selectedDate = splitStudyState.selectedDate,
            selectedIndex = splitStudyState.selectedPieceIndex,
            datePickerBottomSheetState = splitStudyState.datePickerBottomSheetState,
            startPageList = splitStudyState.startPageList,
            endPageList = splitStudyState.endPageList,
            seletedDateList = splitStudyState.dateList,
            onChangeStartPage = {index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnChangeStartPage(index,value))
            },
            onChangeEndPage = {index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnChangeEndPage(index,value))
            },
            onChangeStartPageFocused = {index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnChangeStartPageFocused(index, value))
            },
            onChangeEndPageFocused = {index, value ->
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnChangeEndPageFocused(index, value))
            },
            onClickDatePicker = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnClickDatePicker(it))
            },
            onCloseBottomSheet = {
                viewModel.setEvent(SplitStudyContract.SplitStudyEvent.OnCloseBottomSheet)
            }
        )
    }
}