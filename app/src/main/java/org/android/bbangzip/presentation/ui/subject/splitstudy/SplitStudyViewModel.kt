package org.android.bbangzip.presentation.ui.subject.splitstudy

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.util.base.BaseViewModel
import org.android.bbangzip.presentation.util.date.dateStringToLocalDate
import org.android.bbangzip.presentation.util.date.divideDatesByN
import org.android.bbangzip.presentation.util.date.localDateToDate
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplitStudyViewModel 
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle
    ) : BaseViewModel<SplitStudyContract.SplitStudyEvent, SplitStudyContract.SplitStudyState, SplitStudyContract.SplitStudyReduce, SplitStudyContract.SplitStudySideEffect>(
        savedStateHandle = savedStateHandle,
    ){
        override fun createInitialState(savedState: Parcelable?): SplitStudyContract.SplitStudyState {
            return savedState as? SplitStudyContract.SplitStudyState ?: SplitStudyContract.SplitStudyState()
        }

    override fun reduceState(
        state: SplitStudyContract.SplitStudyState,
        reduce: SplitStudyContract.SplitStudyReduce
    ): SplitStudyContract.SplitStudyState {
        return when(reduce){
            is SplitStudyContract.SplitStudyReduce.UpdateButtonEnabled -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateDatePickerBottomSheetState -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateEndPage -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateEndPageFocusedState -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateEndPageInputState -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateEndPageToString -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateExamDate -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateSelectedDate -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateStartPage -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateStartPageFocusedState -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateStartPageInputState -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateStartPageToString -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdatePieceNumber -> {state}
            is SplitStudyContract.SplitStudyReduce.InitializeState -> {
                state.copy(
                    subjectName = reduce.addStudyData.subjectName,
                    startPage = reduce.addStudyData.startPage,
                    endPage = reduce.addStudyData.endPage,
                    pieceNumber = reduce.addStudyData.pieceNumber,
                    startPageList = reduce.addStudyData.startPageList,
                    endPageList = reduce.addStudyData.endPageList,
                    dateList = divideDatesByN(
                        dateStringToLocalDate( reduce.addStudyData.examDate), reduce.addStudyData.pieceNumber
                    ).map { localDateToDate(it) },
                    )
            }
        }
    }

    override fun handleEvent(event: SplitStudyContract.SplitStudyEvent) {
        when(event){
            is SplitStudyContract.SplitStudyEvent.OnChangeEndPage -> {}
            is SplitStudyContract.SplitStudyEvent.OnChangeEndPageFocused -> {}
            is SplitStudyContract.SplitStudyEvent.OnChangeSelectedDate -> {}
            is SplitStudyContract.SplitStudyEvent.OnChangeStartPage -> {}
            is SplitStudyContract.SplitStudyEvent.OnChangeStartPageFocused -> {}
            is SplitStudyContract.SplitStudyEvent.Initialize -> {
                Timber.d("[쪼개기] Initialize -> ${event.addStudyData}")
                updateState(SplitStudyContract.SplitStudyReduce.InitializeState(addStudyData = event.addStudyData))
            }
            SplitStudyContract.SplitStudyEvent.OnClickBackIcon -> {}
            SplitStudyContract.SplitStudyEvent.OnClickConfirmDateBtn -> {}
            SplitStudyContract.SplitStudyEvent.OnClickDatePicker -> {}
            SplitStudyContract.SplitStudyEvent.OnClickNextBtn -> {}
            SplitStudyContract.SplitStudyEvent.OnClickSaveBtn -> {}
        }
    }
}