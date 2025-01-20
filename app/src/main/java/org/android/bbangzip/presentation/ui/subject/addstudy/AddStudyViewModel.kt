package org.android.bbangzip.presentation.ui.subject.addstudy

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.bbangzip.domain.repository.local.UserRepository
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.type.ShortTextFieldType
import org.android.bbangzip.presentation.util.base.BaseViewModel
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
                updateState(AddStudyContract.AddStudyReduce.UpdateEndPage(endPage = event.endPage))
            }
            is AddStudyContract.AddStudyEvent.OnChangeStartPage -> {
                updateState(AddStudyContract.AddStudyReduce.UpdateStartPage(startPage = event.startPage))
            }
            is AddStudyContract.AddStudyEvent.OnChangeStartPageFocused -> {
                updateState(AddStudyContract.AddStudyReduce.UpdateStartPageFocusedState(startPageFocusedState = event.startPageFocusedState))
            }

            is AddStudyContract.AddStudyEvent.OnChangeEndPageFocused -> {
                updateState(AddStudyContract.AddStudyReduce.UpdateEndPageFocusedState(endPageFocusedState = event.endPageFocusedState))
            }

            is AddStudyContract.AddStudyEvent.OnChangeStudyContent -> {
                updateState(AddStudyContract.AddStudyReduce.UpdateStudyContent(studyContent = event.studyContent))
            }
            is AddStudyContract.AddStudyEvent.OnChangeStudyContentFocused -> {
                updateState(AddStudyContract.AddStudyReduce.UpdateStudyContentFocusedState(studyContentFocusedState = event.studyContentFocusedState))
            }

            is AddStudyContract.AddStudyEvent.OnClickConfirmDateBtn -> {
                updateState(AddStudyContract.AddStudyReduce.UpdateDatePickerBottomSheetState)
                updateState(AddStudyContract.AddStudyReduce.UpdateExamDate)
            }

            is AddStudyContract.AddStudyEvent.OnChangeSelectedDate -> {
                updateState(AddStudyContract.AddStudyReduce.UpdateSelectedDate(date = event.selectedDate))
            }

            AddStudyContract.AddStudyEvent.OnClickBackIcon -> { }
            AddStudyContract.AddStudyEvent.OnClickDatePicker -> {
                updateState(AddStudyContract.AddStudyReduce.UpdateDatePickerBottomSheetState)
            }
            AddStudyContract.AddStudyEvent.OnClickEnrollBtn -> {

            }
            AddStudyContract.AddStudyEvent.OnClickNextBtn -> {

            }
            AddStudyContract.AddStudyEvent.OnClickPieceNumber -> {

            }
            AddStudyContract.AddStudyEvent.OnClickSplitBtn -> {
                updateState(AddStudyContract.AddStudyReduce.UpdatePiecePickerBottomSheetState)
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
                state
            }
        }
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