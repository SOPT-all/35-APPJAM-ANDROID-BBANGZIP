package org.android.bbangzip.presentation.ui.subject.addstudy

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.domain.repository.local.UserRepository
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.type.ShortTextFieldType
import org.android.bbangzip.presentation.ui.subject.addstudy.AddStudyContract.AddStudyReduce
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddStudyViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<AddStudyContract.AddStudyEvent, AddStudyContract.AddStudyState, AddStudyContract.AddStudyReduce, AddStudyContract.AddStudySideEffect>(
    savedStateHandle = savedStateHandle,
    ){
    override fun createInitialState(savedState: Parcelable?): AddStudyContract.AddStudyState {
        return savedState as? AddStudyContract.AddStudyState ?: AddStudyContract.AddStudyState()
    }

    override fun handleEvent(event: AddStudyContract.AddStudyEvent) {
        when(event){
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
                updateState(AddStudyReduce.UpdatePiecePickerBottomSheetState)
                updateState(AddStudyReduce.UpdatePieceNumber(event.pieceNumber))
                setSideEffect(AddStudyContract.AddStudySideEffect.NavigateSplitStudy(subjectName = currentUiState.subjectName))
            }
            AddStudyContract.AddStudyEvent.OnClickSplitBtn -> {
                updateState(AddStudyReduce.UpdatePiecePickerBottomSheetState)
            }
        }
    }

    override fun reduceState(
        state: AddStudyContract.AddStudyState,
        reduce: AddStudyContract.AddStudyReduce
    ): AddStudyContract.AddStudyState {
        return when(reduce) {
            AddStudyContract.AddStudyReduce.UpdateDatePickerBottomSheetState -> {
                state.copy(datePickerBottomSheetState = !state.datePickerBottomSheetState)
            }

            AddStudyContract.AddStudyReduce.UpdatePiecePickerBottomSheetState -> {
                state.copy(piecePickerBottomSheetState = !state.piecePickerBottomSheetState)
            }

            is AddStudyContract.AddStudyReduce.UpdateExamDate -> {
                state.copy(
                    examDate = state.selectedDate.year + "년 " + state.selectedDate.month + "월 " + state.selectedDate.day + "일",
                )
            }

            is AddStudyContract.AddStudyReduce.UpdateEndPage -> {
                state.copy(
                    endPage = reduce.endPage
                )
            }

            is AddStudyContract.AddStudyReduce.UpdateStartPage -> {
                state.copy(
                    startPage = reduce.startPage,
                )
            }

            is AddStudyContract.AddStudyReduce.UpdateStudyContent -> {
                state.copy(studyContent = reduce.studyContent)
            }

            is AddStudyContract.AddStudyReduce.UpdateSelectedDate -> {
                state.copy(
                    selectedDate = reduce.date
                )
            }

            is AddStudyContract.AddStudyReduce.UpdateEndPageFocusedState -> {
                state.copy(
                    endPageFocusedState = reduce.endPageFocusedState
                )
            }
            AddStudyContract.AddStudyReduce.UpdateEndPageInputState -> {
                state
            }
            is AddStudyContract.AddStudyReduce.UpdateStartPageFocusedState ->{
                state.copy(
                    startPageFocusedState = reduce.startPageFocusedState
                )
            }
            AddStudyContract.AddStudyReduce.UpdateStartPageInputState ->{
                state
            }
            is AddStudyContract.AddStudyReduce.UpdateStudyContentFocusedState -> {
                state.copy(
                    studyContentFocusedState = reduce.studyContentFocusedState
                )
            }
            AddStudyContract.AddStudyReduce.UpdateStudyContentInputState -> {
                Timber.d("텍스트필드")
                val checkedText = determineLongTextFieldType(state.studyContent?:"", state.studyContentFocusedState)
                state.copy(studyContentTextFieldState = checkedText)
            }

            is AddStudyContract.AddStudyReduce.UpdatePieceNumber -> {
                state.copy(
                    pieceNumber = reduce.pieceNumber
                )
            }

            is AddStudyContract.AddStudyReduce.UpdateStartPageToString -> {
                state.copy(
                    startPage = if(state.startPage.isNullOrEmpty()) {
                        ""
                    }else {
                        if (state.startPageFocusedState) state.startPage.filter { it.isDigit() }.toInt().toString() else state.startPage.toInt().toString() + "p"
                    },
                )
            }

            is AddStudyContract.AddStudyReduce.UpdateEndPageToString -> {
                state.copy(
                    endPage = if(state.endPage.isNullOrEmpty()) {
                        ""
                    }else {
                        if (state.endPageFocusedState) state.endPage.filter { it.isDigit() }.toInt().toString() else state.endPage.toInt().toString() + "p"
                    },
                )
            }

            is AddStudyContract.AddStudyReduce.UpdateSplitButtonEnabled -> {
                Timber.d("[UpdateSplitButtonEnabled]${state.buttonSplitEnabled}")
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
        }
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