package org.android.bbangzip.presentation.ui.subject.splitstudy

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.util.base.BaseViewModel
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
            SplitStudyContract.SplitStudyReduce.UpdateButtonEnabled -> {state}
            SplitStudyContract.SplitStudyReduce.UpdateDatePickerBottomSheetState -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateEndPage -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateEndPageFocusedState -> {state}
            SplitStudyContract.SplitStudyReduce.UpdateEndPageInputState -> {state}
            SplitStudyContract.SplitStudyReduce.UpdateEndPageToString -> {state}
            SplitStudyContract.SplitStudyReduce.UpdateExamDate -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateSelectedDate -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateStartPage -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateStartPageFocusedState -> {state}
            SplitStudyContract.SplitStudyReduce.UpdateStartPageInputState -> {state}
            SplitStudyContract.SplitStudyReduce.UpdateStartPageToString -> {state}
            is SplitStudyContract.SplitStudyReduce.InitializeState -> {
                state.copy(subjectName = reduce.subjectName)}
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
                updateState(SplitStudyContract.SplitStudyReduce.InitializeState(subjectName = event.subjectName))
            }
            SplitStudyContract.SplitStudyEvent.OnClickBackIcon -> {}
            SplitStudyContract.SplitStudyEvent.OnClickConfirmDateBtn -> {}
            SplitStudyContract.SplitStudyEvent.OnClickDatePicker -> {}
            SplitStudyContract.SplitStudyEvent.OnClickNextBtn -> {}
            SplitStudyContract.SplitStudyEvent.OnClickSaveBtn -> {}
        }
    }
}