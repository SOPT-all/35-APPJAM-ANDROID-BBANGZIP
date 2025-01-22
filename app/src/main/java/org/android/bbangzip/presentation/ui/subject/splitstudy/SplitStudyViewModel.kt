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
            SplitStudyContract.SplitStudyReduce.UpdateButtonEnabled ->{
                state.copy(
                    isSaveEnable = state.startPageList.all { it != "" } && state.endPageList.all { it != "" }
                )
            }
            is SplitStudyContract.SplitStudyReduce.UpdateDatePickerBottomSheetState -> {
                state.copy(
                    datePickerBottomSheetState = !state.datePickerBottomSheetState
                )
            }
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
                    endPageList = state.endPageList.mapIndexed { index, value ->
                        if(index != reduce.index) value
                        else {
                            if(state.endPageList[index].isEmpty()) {
                                ""
                            }else {
                                Timber.d("[endPageFocusedStateList[index]] : ${state.endPageFocusedStateList[index]}")
                                if (state.endPageFocusedStateList[index])
                                    state.endPageList[index].filter { it.isDigit() }.toInt().toString()
                                else
                                    state.endPageList[index].toInt().toString() + "p"

                            }
                        }
                    }
                )
            }
            is SplitStudyContract.SplitStudyReduce.UpdateExamDate -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateSelectedDate -> {
                state.copy(
                    selectedDate = reduce.date
                )
            }
            is SplitStudyContract.SplitStudyReduce.UpdateStartPage -> {
                state.copy(
                    startPageList = state.startPageList.mapIndexed { index, value ->
                        if(index == reduce.index) reduce.startPage else value
                    }
                )
            }
            is SplitStudyContract.SplitStudyReduce.UpdateStartPageFocusedState -> {
                state.copy(
                    startPageFocusedStateList = state.startPageFocusedStateList.mapIndexed { index, value ->
                        if(index == reduce.index) reduce.startPageFocusedState else value
                    }
                )
            }
            is SplitStudyContract.SplitStudyReduce.UpdateStartPageInputState -> {state}
            is SplitStudyContract.SplitStudyReduce.UpdateStartPageToString -> {
                state.copy(
                    startPageList = state.startPageList.mapIndexed { index, value ->
                        if(index != reduce.index) value
                        else {
                            if(state.startPageList[index].isEmpty()) {
                                ""
                            }else {
                                if (state.startPageFocusedStateList[index])
                                    state.startPageList[index].filter { it.isDigit() }.toInt().toString()
                                else
                                    state.startPageList[index].toInt().toString() + "p"

                            }
                        }
                    }
                )
            }
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
                    startPageFocusedStateList = List(reduce.addStudyData.pieceNumber) { false },
                    endPageFocusedStateList = List(reduce.addStudyData.pieceNumber) { false }
                    )
            }
            is SplitStudyContract.SplitStudyReduce.UpdateSeletedIndex -> {
                state.copy(
                    selectedPieceIndex = reduce.index
                )
            }

            is SplitStudyContract.SplitStudyReduce.UpdateState -> {
                state.copy(
                    isSuccess = true
                )
            }

            is SplitStudyContract.SplitStudyReduce.UpdateSelectedDateList -> {
                state.copy(
                    dateList = state.dateList.mapIndexed { index, date ->
                        if(index == currentUiState.selectedPieceIndex+1) currentUiState.selectedDate else date
                    }
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
                updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageToString(index = event.index))
                updateState(SplitStudyContract.SplitStudyReduce.UpdateButtonEnabled)
                Timber.d("[OnChangeEndPageFocused] : ${currentUiState.isSaveEnable}, ${currentUiState.endPageList}")
            }
            is SplitStudyContract.SplitStudyEvent.OnChangeSelectedDate -> {
                updateState(SplitStudyContract.SplitStudyReduce.UpdateSelectedDate(date = event.selectedDate))
            }
            is SplitStudyContract.SplitStudyEvent.OnChangeStartPage -> {
                updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPage(index = event.index, startPage = event.startPage))
            }
            is SplitStudyContract.SplitStudyEvent.OnChangeStartPageFocused -> {
                updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageFocusedState(index = event.index, startPageFocusedState = event.startPageFocusedState))
                updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageToString(index = event.index))
                updateState(SplitStudyContract.SplitStudyReduce.UpdateButtonEnabled)
            }
            is SplitStudyContract.SplitStudyEvent.Initialize -> {
                updateState(SplitStudyContract.SplitStudyReduce.InitializeState(addStudyData = event.addStudyData))
                updateState(SplitStudyContract.SplitStudyReduce.UpdateState)
            }
            SplitStudyContract.SplitStudyEvent.OnClickBackIcon -> {}

            is SplitStudyContract.SplitStudyEvent.OnClickConfirmDateBtn -> {
                updateState(SplitStudyContract.SplitStudyReduce.UpdateSelectedDateList)
                updateState(SplitStudyContract.SplitStudyReduce.UpdateDatePickerBottomSheetState)
            }
            is SplitStudyContract.SplitStudyEvent.OnClickDatePicker -> {
                updateState(SplitStudyContract.SplitStudyReduce.UpdateDatePickerBottomSheetState)
                updateState(SplitStudyContract.SplitStudyReduce.UpdateSeletedIndex(event.index))
                updateState(SplitStudyContract.SplitStudyReduce.UpdateSelectedDate(date = currentUiState.dateList[event.index]))
            }
            SplitStudyContract.SplitStudyEvent.OnClickNextBtn -> {}
            SplitStudyContract.SplitStudyEvent.OnClickSaveBtn -> {}
            SplitStudyContract.SplitStudyEvent.OnCloseBottomSheet -> {
                updateState(SplitStudyContract.SplitStudyReduce.UpdateDatePickerBottomSheetState)
            }
        }
    }
}