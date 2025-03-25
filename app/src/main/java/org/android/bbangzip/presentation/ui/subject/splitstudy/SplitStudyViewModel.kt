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
                SplitStudyContract.SplitStudyReduce.UpdateIsSaveBtnEnabled -> {
                    state.copy(
                        isSaveEnabled =
                            state.startPageList.all { it != "" } &&
                                state.endPageList.all { it != "" } &&
                                state.startPageTextFieldInputStateList.all { it != BbangZipTextFieldInputState.Alert } &&
                                state.endPageTextFieldInputStateList.all { it != BbangZipTextFieldInputState.Alert },
                    )
                }

                is SplitStudyContract.SplitStudyReduce.UpdateIsDatePickerBottomSheetVisible -> {
                    state.copy(
                        isDatePickerBottomSheetVisible = !state.isDatePickerBottomSheetVisible,
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

                is SplitStudyContract.SplitStudyReduce.UpdateIsEndPageFocused -> {
                    state.copy(
                        isEndPageFocusedList =
                            state.isEndPageFocusedList.mapIndexed { index, value ->
                                if (index == reduce.index) reduce.isEndPageFocused else value
                            },
                    )
                }

                is SplitStudyContract.SplitStudyReduce.AddSuffixToEndPage -> {
                    state.copy(
                        endPageList =
                            state.endPageList.mapIndexed { index, value ->
                                if (index != reduce.index) {
                                    value
                                } else {
                                    if (state.endPageList[index].isEmpty()) {
                                        ""
                                    } else {
                                        if (state.isEndPageFocusedList[index]) {
                                            state.endPageList[index].filter { it.isDigit() }
                                        } else {
                                            if (state.endPageList[index].last() == 'p') {
                                                state.endPageList[index]
                                            } else {
                                                state.endPageList[index] + "p"
                                            }
                                        }
                                    }
                                }
                            },
                    )
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

                is SplitStudyContract.SplitStudyReduce.UpdateIsStartPageFocused -> {
                    state.copy(
                        isStartPageFocusedList =
                            state.isStartPageFocusedList.mapIndexed { index, value ->
                                if (index == reduce.index) reduce.isStartPageFocused else value
                            },
                    )
                }

                is SplitStudyContract.SplitStudyReduce.AddSuffixToStartPage -> {
                    state.copy(
                        startPageList =
                            state.startPageList.mapIndexed { index, value ->
                                if (index != reduce.index) {
                                    value
                                } else {
                                    if (state.startPageList[index].isEmpty()) {
                                        ""
                                    } else {
                                        if (state.isStartPageFocusedList[index]) {
                                            state.startPageList[index].filter { it.isDigit() }
                                        } else {
                                            if (state.startPageList[index].last() == 'p') {
                                                state.startPageList[index]
                                            } else {
                                                state.startPageList[index] + "p"
                                            }
                                        }
                                    }
                                }
                            },
                    )
                }

                is SplitStudyContract.SplitStudyReduce.InitializeState -> {
                    state.copy(
                        subjectId = reduce.addStudyData.subjectId,
                        subjectName = reduce.addStudyData.subjectName,
                        startPage = reduce.addStudyData.startPage,
                        examDate = reduce.addStudyData.examDate,
                        studyContent = reduce.addStudyData.studyContent,
                        endPage = reduce.addStudyData.endPage,
                        pieceNumber = reduce.addStudyData.pieceNumber,
                        startPageList = reduce.addStudyData.startPageList,
                        endPageList = reduce.addStudyData.endPageList,
                        examName = reduce.addStudyData.examName,
                        deadlineList =
                            divideDatesByN(dateStringToLocalDate(reduce.addStudyData.examDate), reduce.addStudyData.pieceNumber).map { localDateToDate(it) },
                        isStartPageFocusedList = List(reduce.addStudyData.pieceNumber) { false },
                        isEndPageFocusedList = List(reduce.addStudyData.pieceNumber) { false },
                        startPageGuidelineList = List(reduce.addStudyData.pieceNumber) { "부터" },
                        startPageTextFieldInputStateList = List(reduce.addStudyData.pieceNumber) { BbangZipTextFieldInputState.Default },
                        endPageGuidelineList = List(reduce.addStudyData.pieceNumber) { "까지" },
                        endPageTextFieldInputStateList = List(reduce.addStudyData.pieceNumber) { BbangZipTextFieldInputState.Default },
                    )
                }

                is SplitStudyContract.SplitStudyReduce.UpdateSelectedPieceIndex -> {
                    state.copy(
                        selectedPieceIndex = reduce.index,
                    )
                }

                is SplitStudyContract.SplitStudyReduce.UpdateState -> {
                    state.copy(
                        isSuccess = true,
                    )
                }

                is SplitStudyContract.SplitStudyReduce.UpdateDeadlineList -> {
                    state.copy(
                        deadlineList =
                            state.deadlineList.mapIndexed { index, date ->
                                if (index == currentUiState.selectedPieceIndex + 1) currentUiState.selectedDate else date
                            },
                    )
                }

                is SplitStudyContract.SplitStudyReduce.UpdateStartPageTextFieldInputState -> {
                    state.copy(
                        startPageTextFieldInputStateList =
                            state.startPageTextFieldInputStateList.mapIndexed { index, value ->
                                if (index == reduce.index) determineStartTextFieldType(start = state.startPageList[index], end = state.endPageList[index], min = pageToInt(state.startPage), isFocused = state.isStartPageFocusedList[index]) else value
                            },
                    )
                }

                is SplitStudyContract.SplitStudyReduce.UpdateEndPageTextFieldInputState -> {
                    state.copy(
                        endPageTextFieldInputStateList =
                            state.endPageTextFieldInputStateList.mapIndexed { index, value ->
                                if (index == reduce.index) determineEndTextFieldType(end = state.endPageList[index], max = pageToInt(state.endPage), isFocused = state.isEndPageFocusedList[index]) else value
                            },
                    )
                }

                is SplitStudyContract.SplitStudyReduce.UpdateEndPageGuideline -> {
                    state.copy(
                        endPageGuidelineList =
                            state.endPageGuidelineList.mapIndexed { index, value ->
                                if (index == reduce.index && state.startPageTextFieldInputStateList[index] == BbangZipTextFieldInputState.Alert) {
                                    if (state.startPageList[index] == "0p") {
                                        "0p는 입력할 수 없어요"
                                    } else {
                                        "종료 범위 이전으로 입력해 주세요"
                                    }
                                } else if (state.startPageTextFieldInputStateList[index] == BbangZipTextFieldInputState.Alert) {
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
                                if (index == reduce.index && state.startPageTextFieldInputStateList[index] == BbangZipTextFieldInputState.Alert) {
                                    if (state.startPageList[index] == "0p") {
                                        "0p는 입력할 수 없어요"
                                    } else {
                                        "시작 범위 이후로 입력해 주세요"
                                    }
                                } else if (state.startPageTextFieldInputStateList[index] == BbangZipTextFieldInputState.Alert) {
                                    value
                                } else {
                                    "부터"
                                }
                            },
                    )
                }
            }
        }

        override fun handleEvent(event: SplitStudyContract.SplitStudyEvent) {
            when (event) {
                is SplitStudyContract.SplitStudyEvent.OnEndPageChange -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPage(index = event.index, endPage = event.endPage))
                }

                is SplitStudyContract.SplitStudyEvent.OnIsEndPageFocusedChange -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateIsEndPageFocused(index = event.index, isEndPageFocused = event.isEndPageFocused))
                    updateState(SplitStudyContract.SplitStudyReduce.AddSuffixToEndPage(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageTextFieldInputState(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageTextFieldInputState(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageGuideline(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageGuideline(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateIsSaveBtnEnabled)
                }

                is SplitStudyContract.SplitStudyEvent.OnDeadlineChange -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateSelectedDate(date = event.selectedDate))
                }

                is SplitStudyContract.SplitStudyEvent.OnStartPageChange -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPage(index = event.index, startPage = event.startPage))
                }

                is SplitStudyContract.SplitStudyEvent.OnIsStartPageFocusedChange -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateIsStartPageFocused(index = event.index, isStartPageFocused = event.isStartPageFocused))
                    updateState(SplitStudyContract.SplitStudyReduce.AddSuffixToStartPage(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageTextFieldInputState(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageTextFieldInputState(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateStartPageGuideline(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateEndPageGuideline(index = event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateIsSaveBtnEnabled)
                }

                is SplitStudyContract.SplitStudyEvent.Initialize -> {
                    updateState(SplitStudyContract.SplitStudyReduce.InitializeState(addStudyData = event.addStudyData))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateState)
                }

                is SplitStudyContract.SplitStudyEvent.OnBackIconClick -> {
                    setSideEffect(SplitStudyContract.SplitStudySideEffect.NavigateToAddStudy(event.splitStudyData))
                }

                is SplitStudyContract.SplitStudyEvent.OnConfirmDateBtnClick -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateDeadlineList)
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateIsDatePickerBottomSheetVisible)
                }

                is SplitStudyContract.SplitStudyEvent.OnDatePickerClick -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateIsDatePickerBottomSheetVisible)
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateSelectedPieceIndex(event.index))
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateSelectedDate(date = currentUiState.deadlineList[event.index]))
                }

                is SplitStudyContract.SplitStudyEvent.OnSaveBtnClick -> {
                    setSideEffect(
                        SplitStudyContract.SplitStudySideEffect.NavigateToAddStudy(
                            splitStudyData =
                                SplitStudyData(
                                    subjectId = event.splitStudyData.subjectId,
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

                SplitStudyContract.SplitStudyEvent.OnDatePickerBottomSheetDismissRequest -> {
                    updateState(SplitStudyContract.SplitStudyReduce.UpdateIsDatePickerBottomSheetVisible)
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
