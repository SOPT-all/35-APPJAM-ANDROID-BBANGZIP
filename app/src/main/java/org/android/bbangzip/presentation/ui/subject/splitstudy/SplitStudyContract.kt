package org.android.bbangzip.presentation.ui.subject.splitstudy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.model.Date
import org.android.bbangzip.presentation.util.base.BaseContract

class SplitStudyContract {
    @Parcelize
    data class SplitStudyState(
        val subjectName: String = "경제통계학",
        val startPage: String = "",
        val endPage: String = "",
        val pieceNumber: Int = 0,
        val startPageList: List<String> = listOf(),
        val startPageFocusedList: List<Boolean> = listOf(),
        val startPageTextFieldStateList: List<BbangZipTextFieldInputState> = listOf(),
        val endPageList: List<String> = listOf(),
        val endPageFocusedStateList: List<Boolean> = listOf(),
        val endPageTextFieldStateList: List<BbangZipTextFieldInputState> = listOf(),
        val dateList: List<Date> = listOf(),
        val datePickerBottomSheetStateList: List<Boolean> = listOf(),
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface SplitStudyEvent : BaseContract.Event {
        data class Initialize(val addStudyData: AddStudyData) : SplitStudyEvent

        data class OnChangeStartPage(val startPage: String) : SplitStudyEvent

        data class OnChangeEndPage(val endPage: String) : SplitStudyEvent

        data class OnChangeStartPageFocused(val startPageFocusedState: Boolean) : SplitStudyEvent

        data class OnChangeEndPageFocused(val endPageFocusedState: Boolean) : SplitStudyEvent

        data class OnChangeSelectedDate(val selectedDate: Date) : SplitStudyEvent

        data object OnClickDatePicker : SplitStudyEvent

        data object OnClickBackIcon : SplitStudyEvent

        data object OnClickNextBtn : SplitStudyEvent

        data object OnClickSaveBtn : SplitStudyEvent

        data object OnClickConfirmDateBtn : SplitStudyEvent
    }

    sealed interface SplitStudyReduce : BaseContract.Reduce {
        data class InitializeState(val addStudyData: AddStudyData) : SplitStudyReduce

        data class UpdateStartPage(val startPage: String) : SplitStudyReduce

        data class UpdateStartPageFocusedState(val startPageFocusedState: Boolean) : SplitStudyReduce

        data object UpdateStartPageInputState : SplitStudyReduce

        data class UpdateEndPage(val endPage: String) : SplitStudyReduce

        data class UpdateEndPageFocusedState(val endPageFocusedState: Boolean) : SplitStudyReduce

        data object UpdateEndPageInputState : SplitStudyReduce

        data object UpdateExamDate : SplitStudyReduce

        data class UpdateSelectedDate(val date: Date) : SplitStudyReduce

        data object UpdateDatePickerBottomSheetState : SplitStudyReduce

        data object UpdateButtonEnabled : SplitStudyReduce

        data object UpdateStartPageToString : SplitStudyReduce

        data object UpdateEndPageToString : SplitStudyReduce

        data object UpdatePieceNumber: SplitStudyReduce
    }

    sealed interface SplitStudySideEffect : BaseContract.SideEffect {
        data object NavigateAddStudy : SplitStudySideEffect
    }
}