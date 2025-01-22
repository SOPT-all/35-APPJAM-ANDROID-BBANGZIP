package org.android.bbangzip.presentation.ui.subject.splitstudy

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.util.base.BaseViewModel
import org.android.bbangzip.presentation.util.date.dateStringToLocalDate
import org.android.bbangzip.presentation.util.date.divideDatesByN
import org.android.bbangzip.presentation.util.date.localDateToDate
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
            is SplitStudyContract.SplitStudyReduce.UpdateEndPage -> {
                state.copy(
                    endPageList = state.endPageList.mapIndexed { index, value ->
                        if(index == reduce.index) reduce.endPage else value
                    }
                )
            }
            is SplitStudyContract.SplitStudyReduce.UpdateEndPageFocusedState -> {
                state.copy(
                    endPageFocusedStateList = state.endPageFocusedStateList.mapIndexed { index, value ->
                        if(index == reduce.index) reduce.endPageFocusedState else value
                    }
                )
            }
            is SplitStudyContract.SplitStudyReduce.UpdateEndPageInputState -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateEndPageToString -> {
                state.copy(
                    endPageList = List(state.endPageList.size) { index ->  if(state.endPageList[index].isEmpty()) {
                        ""
                    }else {
                        if (state.endPageFocusedStateList[index])
                            state.endPageList[index].filter { it.isDigit() }.toInt().toString()
                        else state.endPageList[index].toInt().toString() + "p"
                    }
                    })
            }
            is SplitStudyContract.SplitStudyReduce.UpdateExamDate -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateSelectedDate -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateStartPage -> {
                state.copy(
                    startPageList = state.startPageList.mapIndexed { index, value ->
                        if(index == reduce.index) reduce.startPage else value
                    }
                )
            }
            is SplitStudyContract.SplitStudyReduce.UpdateStartPageFocusedState -> {
                state.copy(
                    startPageFocusedList = state.startPageFocusedList.mapIndexed { index, value ->
                        if(index == reduce.index) reduce.startPageFocusedState else value
                    }
                )
            }
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
            is SplitStudyContract.SplitStudyEvent.OnChangeEndPage -> {
                updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPage(index = event.index, endPage = event.endPage))
            }
            is SplitStudyContract.SplitStudyEvent.OnChangeEndPageFocused -> {
                updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageFocusedState(index = event.index, endPageFocusedState = event.endPageFocusedState))
            }
            is SplitStudyContract.SplitStudyEvent.OnChangeSelectedDate -> {}
            is SplitStudyContract.SplitStudyEvent.OnChangeStartPage -> {
                updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPage(index = event.index, startPage = event.startPage))
            }
            is SplitStudyContract.SplitStudyEvent.OnChangeStartPageFocused -> {
                updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageFocusedState(index = event.index, startPageFocusedState = event.startPageFocusedState))
            }
            is SplitStudyContract.SplitStudyEvent.Initialize -> {
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