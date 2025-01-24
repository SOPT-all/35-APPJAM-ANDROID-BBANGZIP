package org.android.bbangzip.presentation.ui.subject.splitstudy

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.model.SplitStudyData
import org.android.bbangzip.presentation.util.base.BaseViewModel
import org.android.bbangzip.presentation.util.casting.pageToInt
import org.android.bbangzip.presentation.util.date.dateStringToLocalDate
import org.android.bbangzip.presentation.util.date.divideDatesByN
import org.android.bbangzip.presentation.util.date.localDateToDate
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplitStudyViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<SplitStudyContract.SplitStudyEvent, SplitStudyContract.SplitStudyState, SplitStudyContract.SplitStudyReduce, SplitStudyContract.SplitStudySideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): SplitStudyContract.SplitStudyState {
            return savedState as? SplitStudyContract.SplitStudyState ?: SplitStudyContract.SplitStudyState()
        }

        override fun reduceState(
            state: SplitStudyContract.SplitStudyState,
            reduce: SplitStudyContract.SplitStudyReduce,
        ): SplitStudyContract.SplitStudyState {
            return when (reduce) {
                SplitStudyContract.SplitStudyReduce.UpdateButtonEnabled -> {
                    state.copy(
                        isSaveEnable =
                            state.startPageList.all { it != "" } &&
                                state.endPageList.all { it != "" } &&
                                state.startPageTextFieldStateList.all { it != BbangZipTextFieldInputState.Alert } &&
                                state.endPageTextFieldStateList.all { it != BbangZipTextFieldInputState.Alert },
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateDatePickerBottomSheetState -> {
                    state.copy(
                        datePickerBottomSheetState = !state.datePickerBottomSheetState,
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateEndPage -> {
                    state.copy(
                        endPageList =
                            state.endPageList.mapIndexed { index, value ->
                                if (index == reduce.index) reduce.endPage else value
                            },
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateEndPageFocusedState -> {
                    state.copy(
                        endPageFocusedStateList =
                            state.endPageFocusedStateList.mapIndexed { index, value ->
                                if (index == reduce.index) reduce.endPageFocusedState else value
                            },
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateEndPageInputState -> {
                    state
                }
                is SplitStudyContract.SplitStudyReduce.UpdateEndPageToString -> {
                    state.copy(
                        endPageList =
                            state.endPageList.mapIndexed { index, value ->
                                if (index != reduce.index) {
                                    value
                                } else {
                                    if (state.endPageList[index].isEmpty()) {
                                        ""
                                    } else {
                                        if (state.endPageFocusedStateList[index]) {
                                            state.endPageList[index].filter { it.isDigit() }.toInt().toString()
                                        } else {
                                            if (state.endPageList[index].last() == 'p') {
                                                state.endPageList[index].dropLast(1)
                                            } else {
                                                state.endPageList[index].toInt().toString() + "p"
                                            }
                                        }
                                    }
                                }
                            },
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateExamDate -> {
                    state
                }
                is SplitStudyContract.SplitStudyReduce.UpdateSelectedDate -> {
                    state.copy(
                        selectedDate = reduce.date,
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateStartPage -> {
                    state.copy(
                        startPageList =
                            state.startPageList.mapIndexed { index, value ->
                                if (index == reduce.index) reduce.startPage else value
                            },
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateStartPageFocusedState -> {
                    state.copy(
                        startPageFocusedStateList =
                            state.startPageFocusedStateList.mapIndexed { index, value ->
                                if (index == reduce.index) reduce.startPageFocusedState else value
                            },
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateStartPageInputState -> {
                    state
                }
                is SplitStudyContract.SplitStudyReduce.UpdateStartPageToString -> {
                    state.copy(
                        startPageList =
                            state.startPageList.mapIndexed { index, value ->
                                if (index != reduce.index) {
                                    value
                                } else {
                                    if (state.startPageList[index].isEmpty()) {
                                        ""
                                    } else {
                                        if (state.startPageFocusedStateList[index]) {
                                            state.startPageList[index].filter { it.isDigit() }.toInt().toString()
                                        } else {
                                            if (state.startPageList[index].last() == 'p') {
                                                state.startPageList[index].dropLast(1)
                                            } else {
                                                state.startPageList[index].toInt().toString() + "p"
                                            }
                                        }
                                    }
                                }
                            },
                    )
                }

                is SplitStudyContract.SplitStudyReduce.InitializeState -> {
                    state.copy(
                        subjectName = reduce.addStudyData.subjectName,
                        startPage = reduce.addStudyData.startPage,
                        examDate = reduce.addStudyData.examDate,
                        studyContent = reduce.addStudyData.studyContent,
                        endPage = reduce.addStudyData.endPage,
                        pieceNumber = reduce.addStudyData.pieceNumber,
                        startPageList = reduce.addStudyData.startPageList,
                        endPageList = reduce.addStudyData.endPageList,
                        examName = reduce.addStudyData.examName,
                        dateList =
                            divideDatesByN(dateStringToLocalDate(reduce.addStudyData.examDate), reduce.addStudyData.pieceNumber).map { localDateToDate(it) },
                        startPageFocusedStateList = List(reduce.addStudyData.pieceNumber) { false },
                        endPageFocusedStateList = List(reduce.addStudyData.pieceNumber) { false },
                        startPageGuidelineList = List(reduce.addStudyData.pieceNumber) { "부터" },
                        startPageTextFieldStateList = List(reduce.addStudyData.pieceNumber) { BbangZipTextFieldInputState.Default },
                        endPageGuidelineList = List(reduce.addStudyData.pieceNumber) { "까지" },
                        endPageTextFieldStateList = List(reduce.addStudyData.pieceNumber) { BbangZipTextFieldInputState.Default },
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateSeletedIndex -> {
                    state.copy(
                        selectedPieceIndex = reduce.index,
                    )
                }

                is SplitStudyContract.SplitStudyReduce.UpdateState -> {
                    state.copy(
                        isSuccess = true,
                    )
                }

                is SplitStudyContract.SplitStudyReduce.UpdateSelectedDateList -> {
                    state.copy(
                        dateList =
                            state.dateList.mapIndexed { index, date ->
                                if (index == currentUiState.selectedPieceIndex + 1) currentUiState.selectedDate else date
                            },
                    )
                }

                is SplitStudyContract.SplitStudyReduce.UpdateStartPageTextFieldState -> {
                    state.copy(
                        startPageTextFieldStateList =
                            state.startPageTextFieldStateList.mapIndexed { index, value ->
                                if (index == reduce.index) determineStartTextFieldType(start = state.startPageList[index], end = state.endPageList[index], min = pageToInt(state.startPage), isFocused = state.startPageFocusedStateList[index]) else value
                            },
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateEndPageTextFieldState -> {
                    state.copy(
                        endPageTextFieldStateList =
                            state.endPageTextFieldStateList.mapIndexed { index, value ->
                                if (index == reduce.index) determineEndTextFieldType(start = state.startPageList[index], end = state.endPageList[index], max = pageToInt(state.endPage), isFocused = state.endPageFocusedStateList[index]) else value
                            },
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateEndPageGuideline -> {
                    state.copy(
                        endPageGuidelineList =
                            state.endPageGuidelineList.mapIndexed { index, value ->
                                if (index == reduce.index && state.startPageTextFieldStateList[index] == BbangZipTextFieldInputState.Alert) {
                                    if (state.startPageList[index] == "0p") {
                                        "0p는 입력할 수 없어요"
                                    } else {
                                        "종료 범위 이전으로 입력해 주세요"
                                    }
                                } else if (state.startPageTextFieldStateList[index] == BbangZipTextFieldInputState.Alert) {
                                    value
                                } else {
                                    "까지"
                                }
                            },
                    )
                }
                is SplitStudyContract.SplitStudyReduce.UpdateStartPageGuideline -> {
                    state.copy(
                        startPageGuidelineList =
                            state.startPageGuidelineList.mapIndexed { index, value ->
                                if (index == reduce.index && state.startPageTextFieldStateList[index] == BbangZipTextFieldInputState.Alert) {
                                    if (state.startPageList[index] == "0p") {
                                        "0p는 입력할 수 없어요"
                                    } else {
                                        "시작 범위 이후로 입력해 주세요"
                                    }
                                } else if (state.startPageTextFieldStateList[index] == BbangZipTextFieldInputState.Alert) {
                                    value
                                } else {
                                    "부터"
                                }
                            },
                    )
                }

                SplitStudyContract.SplitStudyReduce.UpdatePieceNumber -> {
                    state
                }
            }
        }

        override fun handleEvent(event: SplitStudyContract.SplitStudyEvent) {
            when (event) {
                is SplitStudyContract.SplitStudyEvent.OnChangeEndPage -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPage(index = event.index, endPage = event.endPage))
                }
                is SplitStudyContract.SplitStudyEvent.OnChangeEndPageFocused -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageFocusedState(index = event.index, endPageFocusedState = event.endPageFocusedState))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageToString(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageTextFieldState(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageTextFieldState(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageGuideline(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageGuideline(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateButtonEnabled)
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
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageTextFieldState(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageTextFieldState(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageGuideline(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageGuideline(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateButtonEnabled)
                }
                is SplitStudyContract.SplitStudyEvent.Initialize -> {
                    Timber.d("[Initialize] : ${event.addStudyData}")
                    updateState(SplitStudyContract.SplitStudyReduce.InitializeState(addStudyData = event.addStudyData))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateState)
                }
                SplitStudyContract.SplitStudyEvent.OnClickBackIcon -> {
                    setSideEffect(SplitStudyContract.SplitStudySideEffect.NavigateBack)
                }

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

                is SplitStudyContract.SplitStudyEvent.OnClickSaveBtn -> {
                    Timber.d("[OnClickSaveBtn] : ${event.splitStudyData}")
                    setSideEffect(
                        SplitStudyContract.SplitStudySideEffect.NavigateAddStudy(
                            splitStudyData =
                                SplitStudyData(
                                    subjectName = event.splitStudyData.subjectName,
                                    pieceNumber = event.splitStudyData.pieceNumber,
                                    examDate = event.splitStudyData.examDate,
                                    studyContent = event.splitStudyData.studyContent,
                                    startPageList = event.splitStudyData.startPageList,
                                    endPageList = event.splitStudyData.endPageList,
                                    deadLineList = event.splitStudyData.deadLineList,
                                    startPage = event.splitStudyData.startPage,
                                    endPage = event.splitStudyData.endPage,
                                    addStudyViewType = event.splitStudyData.addStudyViewType,
                                    examName = event.splitStudyData.examName,
                                ),
                        ),
                    )
                }
                SplitStudyContract.SplitStudyEvent.OnCloseBottomSheet -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateDatePickerBottomSheetState)
                }
            }
        }
    }

private fun determineStartTextFieldType(
    start: String,
    end: String,
    min: Int,
    isFocused: Boolean,
): BbangZipTextFieldInputState {
    return when {
        start.isEmpty() && !isFocused -> BbangZipTextFieldInputState.Default
        start.isEmpty() && isFocused -> BbangZipTextFieldInputState.Placeholder
        start == "0p" || (end.isNotEmpty() && pageToInt(start) > pageToInt(end)) || pageToInt(start) < min -> BbangZipTextFieldInputState.Alert
        start.isNotEmpty() && isFocused -> BbangZipTextFieldInputState.Typing
        else -> BbangZipTextFieldInputState.Field
    }
}

private fun determineEndTextFieldType(
    start: String,
    end: String,
    max: Int,
    isFocused: Boolean,
): BbangZipTextFieldInputState {
    return when {
        end.isEmpty() && !isFocused -> BbangZipTextFieldInputState.Default
        end.isEmpty() && isFocused -> BbangZipTextFieldInputState.Placeholder
        end == "0p" || pageToInt(end) > max -> BbangZipTextFieldInputState.Alert
        end.isNotEmpty() && isFocused -> BbangZipTextFieldInputState.Typing
        else -> BbangZipTextFieldInputState.Field
    }
}
