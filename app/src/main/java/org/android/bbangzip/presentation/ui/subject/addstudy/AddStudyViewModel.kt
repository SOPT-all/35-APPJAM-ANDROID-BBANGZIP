package org.android.bbangzip.presentation.ui.subject.addstudy

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.domain.repository.local.UserRepository
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.type.AddStudyViewType
import org.android.bbangzip.presentation.type.ShortTextFieldType
import org.android.bbangzip.presentation.ui.subject.addstudy.AddStudyContract.AddStudyReduce
import org.android.bbangzip.presentation.ui.subject.splitstudy.SplitStudyContract
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddStudyViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<AddStudyContract.AddStudyEvent, AddStudyContract.AddStudyState, AddStudyReduce, AddStudyContract.AddStudySideEffect>(
    savedStateHandle = savedStateHandle,
    ){

    override fun createInitialState(savedState: Parcelable?): AddStudyContract.AddStudyState {
        return savedState as? AddStudyContract.AddStudyState ?: AddStudyContract.AddStudyState()
    }

    override fun handleEvent(event: AddStudyContract.AddStudyEvent) {
        when(event){
            is AddStudyContract.AddStudyEvent.Initialize -> {
                updateState(AddStudyReduce.Initialize(splitStudyData = event.splitStudyData))
                updateState(AddStudyReduce.UpdateSplitButtonEnabled)
            }
            is AddStudyContract.AddStudyEvent.OnChangeEndPage -> {
                updateState(AddStudyReduce.UpdateEndPage(endPage = event.endPage))
            }
            is AddStudyContract.AddStudyEvent.OnChangeStartPage -> {
                updateState(AddStudyReduce.UpdateStartPage(startPage = event.startPage))
            }
            is AddStudyContract.AddStudyEvent.OnChangeStartPageFocused -> {
                updateState(AddStudyReduce.UpdateStartPageFocusedState(startPageFocusedState = event.startPageFocusedState))
                updateState(AddStudyReduce.UpdateStartPageToString)
                updateState(AddStudyReduce.UpdateSplitButtonEnabled)
                updateState(AddStudyReduce.UpdateButtonEnabled)
            }

            is AddStudyContract.AddStudyEvent.OnChangeEndPageFocused -> {
                updateState(AddStudyReduce.UpdateEndPageFocusedState(endPageFocusedState = event.endPageFocusedState))
                updateState(AddStudyReduce.UpdateEndPageToString)
                updateState(AddStudyReduce.UpdateSplitButtonEnabled)
                updateState(AddStudyReduce.UpdateButtonEnabled)
            }

            is AddStudyContract.AddStudyEvent.OnChangeStudyContent -> {
                Timber.d("studyContent : ${event.studyContent}")
                updateState(AddStudyReduce.UpdateStudyContent(studyContent = event.studyContent))
                updateState(AddStudyReduce.UpdateStudyContentInputState)
            }
            is AddStudyContract.AddStudyEvent.OnChangeStudyContentFocused -> {
                updateState(AddStudyReduce.UpdateStudyContentFocusedState(studyContentFocusedState = event.studyContentFocusedState))
                updateState(AddStudyReduce.UpdateStudyContentInputState)
                updateState(AddStudyReduce.UpdateButtonEnabled)
            }

            is AddStudyContract.AddStudyEvent.OnClickConfirmDateBtn -> {
                updateState(AddStudyReduce.UpdateDatePickerBottomSheetState)
                updateState(AddStudyReduce.UpdateExamDate)
                updateState(AddStudyReduce.UpdateButtonEnabled)
            }

            is AddStudyContract.AddStudyEvent.OnChangeSelectedDate -> {
                updateState(AddStudyReduce.UpdateSelectedDate(date = event.selectedDate))
            }

            AddStudyContract.AddStudyEvent.OnClickBackIcon -> { }
            AddStudyContract.AddStudyEvent.OnClickDatePicker -> {
                updateState(AddStudyReduce.UpdateDatePickerBottomSheetState)
            }
            AddStudyContract.AddStudyEvent.OnClickEnrollBtn -> {
            }
            AddStudyContract.AddStudyEvent.OnClickNextBtn -> {

            }
            AddStudyContract.AddStudyEvent.OnClickCancleIcon ->{
                updateState(AddStudyReduce.ResetStudyContent)
            }
            is AddStudyContract.AddStudyEvent.OnClickPieceNumber -> {
                updateState(AddStudyReduce.UpdateAddStudyViewType)
                updateState(AddStudyReduce.UpdatePieceNumber(pieceNumber = event.pieceNumber))
                updateState(AddStudyReduce.UpdatePiecePickerBottomSheetState)
                setSideEffect(AddStudyContract.AddStudySideEffect.NavigateSplitStudy(addStudyData =
                    AddStudyData(
                        subjectName = currentUiState.subjectName,
                        pieceNumber = event.pieceNumber,
                        examDate = currentUiState.examDate,
                        studyContent = currentUiState.studyContent?:"",
                        startPage = currentUiState.startPage?:"",
                        endPage = currentUiState.endPage?:"",
                        startPageList = divideRangeIntoInts(currentUiState.startPage!!.filter { it.isDigit() }.toInt(), currentUiState.endPage!!.filter { it.isDigit() }.toInt(), event.pieceNumber).map { it.toString() }.subList(0,event.pieceNumber),
                        endPageList = divideRangeIntoInts(currentUiState.startPage!!.filter { it.isDigit() }.toInt(), currentUiState.endPage!!.filter { it.isDigit() }.toInt(), event.pieceNumber).map { it.toString() }.subList(1,event.pieceNumber+1),
                    )
                    )
                )
                updateState(AddStudyReduce.UpdateIsSuccess)
            }

            AddStudyContract.AddStudyEvent.OnClickSplitBtn -> {
                updateState(AddStudyReduce.UpdatePiecePickerBottomSheetState)
            }

            is AddStudyContract.AddStudyEvent.OnClickAgainSplitBtn -> {
                updateState(AddStudyReduce.UpdateAddStudyViewType)
                updateState(AddStudyReduce.UpdatePieceNumber(pieceNumber = event.pieceNumber))
                setSideEffect(AddStudyContract.AddStudySideEffect.NavigateSplitStudy(addStudyData =
                AddStudyData(
                    subjectName = currentUiState.subjectName,
                    pieceNumber = event.pieceNumber,
                    examDate = currentUiState.examDate,
                    studyContent = currentUiState.studyContent?:"",
                    startPage = currentUiState.startPage?:"",
                    endPage = currentUiState.endPage?:"",
                    startPageList = divideRangeIntoInts(currentUiState.startPage!!.filter { it.isDigit() }.toInt(), currentUiState.endPage!!.filter { it.isDigit() }.toInt(), event.pieceNumber).map { it.toString() }.subList(0,event.pieceNumber),
                    endPageList = divideRangeIntoInts(currentUiState.startPage!!.filter { it.isDigit() }.toInt(), currentUiState.endPage!!.filter { it.isDigit() }.toInt(), event.pieceNumber).map { it.toString() }.subList(1,event.pieceNumber+1),
                )
                )
                )
            }
        }
    }

    override fun reduceState(
        state: AddStudyContract.AddStudyState,
        reduce: AddStudyReduce
    ): AddStudyContract.AddStudyState {
        return when(reduce) {
            is AddStudyReduce.Initialize -> {
                state.copy(
                    subjectName = reduce.splitStudyData.subjectName,
                    pieceNumber = reduce.splitStudyData.pieceNumber,
                    examDate = reduce.splitStudyData.examDate,
                    studyContent = reduce.splitStudyData.studyContent,
                    startPage = reduce.splitStudyData.startPage,
                    endPage = reduce.splitStudyData.endPage,
                    startPageList = reduce.splitStudyData.startPageList,
                    endPageList = reduce.splitStudyData.endPageList,
                    deadLineList = reduce.splitStudyData.deadLineList,
                    addStudyViewType = reduce.splitStudyData.addStudyViewType,
                    isSuccess = true
                )
            }

            AddStudyReduce.UpdateDatePickerBottomSheetState -> {
                state.copy(datePickerBottomSheetState = !state.datePickerBottomSheetState)
            }

            AddStudyReduce.UpdatePiecePickerBottomSheetState -> {
                state.copy(piecePickerBottomSheetState = !state.piecePickerBottomSheetState)
            }

            is AddStudyReduce.UpdateExamDate -> {
                state.copy(
                    examDate = state.selectedDate.year + "년 " + state.selectedDate.month + "월 " + state.selectedDate.day + "일",
                )
            }

            is AddStudyReduce.UpdateEndPage -> {
                state.copy(
                    endPage = reduce.endPage
                )
            }

            is AddStudyReduce.UpdateStartPage -> {
                state.copy(
                    startPage = reduce.startPage,
                )
            }

            is AddStudyReduce.UpdateStudyContent -> {
                state.copy(studyContent = reduce.studyContent)
            }

            is AddStudyReduce.UpdateSelectedDate -> {
                state.copy(
                    selectedDate = reduce.date
                )
            }

            is AddStudyReduce.UpdateEndPageFocusedState -> {
                state.copy(
                    endPageFocusedState = reduce.endPageFocusedState
                )
            }
            AddStudyReduce.UpdateEndPageInputState -> {
                state
            }
            is AddStudyReduce.UpdateStartPageFocusedState ->{
                state.copy(
                    startPageFocusedState = reduce.startPageFocusedState
                )
            }
            AddStudyReduce.UpdateStartPageInputState ->{
                state
            }
            is AddStudyReduce.UpdateStudyContentFocusedState -> {
                state.copy(
                    studyContentFocusedState = reduce.studyContentFocusedState
                )
            }
            AddStudyReduce.UpdateStudyContentInputState -> {
                val checkedText = determineLongTextFieldType(state.studyContent?:"", state.studyContentFocusedState)
                state.copy(studyContentTextFieldState = checkedText)
            }

            is AddStudyReduce.UpdatePieceNumber -> {
                state.copy(
                    pieceNumber = reduce.pieceNumber
                )
            }

            is AddStudyReduce.UpdateStartPageToString -> {
                state.copy(
                    startPage = if(state.startPage.isNullOrEmpty()) {
                        ""
                    }else {
                        if (state.startPageFocusedState)
                            state.startPage.filter { it.isDigit() }.toInt().toString()
                        else
                            if(state.startPage.last()=='p')
                                state.startPage.dropLast(1)
                            else
                                state.startPage.toInt().toString() + "p"
                    },
                )
            }

            is AddStudyReduce.UpdateEndPageToString -> {
                state.copy(
                    endPage = if(state.endPage.isNullOrEmpty()) {
                        ""
                    }else {
                        if (state.endPageFocusedState)
                            state.endPage.filter { it.isDigit() }.toInt().toString()
                        else
                            if(state.endPage.last()=='p')
                                state.endPage.dropLast(1)
                            else
                                state.endPage.toInt().toString() + "p"
                    },
                )
            }

            is AddStudyReduce.UpdateSplitButtonEnabled -> {
                state.copy(
                    buttonSplitEnabled = !(state.startPage.isNullOrEmpty() || state.endPage.isNullOrEmpty() || unitTextToInt(state.startPage) > unitTextToInt(state.endPage))
                )
            }

            is AddStudyReduce.UpdateButtonEnabled -> {
                state.copy(
                    buttonEnabled = state.examDate != "시험 일자 입력" && !state.studyContent.isNullOrEmpty() && state.buttonSplitEnabled
                )
            }

            is AddStudyReduce.ResetStudyContent -> {
                state.copy(
                    studyContent = ""
                )
            }
            AddStudyReduce.UpdateAddStudyViewType -> {
                state.copy(
                    addStudyViewType = AddStudyViewType.AGAIN
                )
            }

            AddStudyReduce.UpdateIsSuccess -> {
                state.copy(
                    isSuccess = false
                )
            }
        }
    }

    private fun divideRangeIntoInts(start: Int, end: Int, n: Int): List<String> {
        require(n > 0) { "n must be greater than 0" }
        require(start <= end) { "Start must be less than or equal to end" }

        val step = (end - start) / n
        val result = mutableListOf<Int>()

        for (i in 0..n) {
            result.add(start + i * step)
        }

        // 마지막 값을 강제로 `end`로 설정 (정수 나눗셈 오차 보정)
        if (result.last() != end) {
            result[result.size - 1] = end
        }


        return result.map{it.toString()}
    }

    private fun unitTextToInt(text: String): Int{
        return text.filter { it.isDigit() }.toInt()
    }

    private fun determineLongTextFieldType(
        text: String,
        isFocused: Boolean,
    ): BbangZipTextFieldInputState {
        return when {
            text.isEmpty() && !isFocused -> BbangZipTextFieldInputState.Default
            text.isEmpty() && isFocused -> BbangZipTextFieldInputState.Placeholder
            text.contains(Regex("[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9 ]")) -> BbangZipTextFieldInputState.Alert
            text.isNotEmpty() && isFocused -> BbangZipTextFieldInputState.Typing
            else -> BbangZipTextFieldInputState.Field
        }
    }

    private fun determineShortTextFieldType(
        text: String,
        isFocused: Boolean,
        shortTextFieldType: ShortTextFieldType
    ): BbangZipTextFieldInputState {
        return when {
            text.isEmpty() && !isFocused -> BbangZipTextFieldInputState.Default
            text.isEmpty() && isFocused -> BbangZipTextFieldInputState.Placeholder
            text.contains(Regex("[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9 ]")) -> BbangZipTextFieldInputState.Alert
            text.isNotEmpty() && isFocused -> BbangZipTextFieldInputState.Typing
            else -> BbangZipTextFieldInputState.Field
        }
    }

    }