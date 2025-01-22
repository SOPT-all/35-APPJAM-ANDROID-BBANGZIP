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
        val isSuccess: Boolean = false,
        val subjectName: String = "경제통계학",
        val startPage: String = "",
        val endPage: String = "",
        val pieceNumber: Int = 0,
        val selectedDate: Date = Date("2025", "1", "21"),
        val selectedPieceIndex: Int = 0,
        val startPageList: List<String> = List(pieceNumber) { "" },
        val startPageFocusedStateList: List<Boolean> = List(pieceNumber) { false },
        val startPageTextFieldStateList: List<BbangZipTextFieldInputState> = List(pieceNumber) { BbangZipTextFieldInputState.Default },
        val endPageList: List<String> = List(pieceNumber) { "" },
        val endPageFocusedStateList: List<Boolean> = List(pieceNumber) { false},
        val endPageTextFieldStateList: List<BbangZipTextFieldInputState> = List(pieceNumber) { BbangZipTextFieldInputState.Default },
        val dateList: List<Date> = List(pieceNumber) { Date("2025", "1", "21") },
        val datePickerBottomSheetState: Boolean = false,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface SplitStudyEvent : BaseContract.Event {
        data class Initialize(val addStudyData: AddStudyData) : SplitStudyEvent

        data class OnChangeStartPage(val index: Int, val startPage: String) : SplitStudyEvent

        data class OnChangeEndPage(val index: Int, val endPage: String) : SplitStudyEvent

        data class OnChangeStartPageFocused(val index: Int, val startPageFocusedState: Boolean) : SplitStudyEvent

        data class OnChangeEndPageFocused(val index: Int, val endPageFocusedState: Boolean) : SplitStudyEvent

        data class OnChangeSelectedDate(val selectedDate: Date) : SplitStudyEvent

        data class OnClickDatePicker(val index: Int) : SplitStudyEvent

        data object OnClickBackIcon : SplitStudyEvent

        data object OnClickNextBtn : SplitStudyEvent

        data object OnClickSaveBtn : SplitStudyEvent

        data object OnClickConfirmDateBtn : SplitStudyEvent

        data object OnCloseBottomSheet : SplitStudyEvent
    }

    sealed interface SplitStudyReduce : BaseContract.Reduce {
        data class InitializeState(val addStudyData: AddStudyData) : SplitStudyReduce

        data class UpdateStartPage(val index: Int, val startPage: String) : SplitStudyReduce

        data class UpdateStartPageFocusedState(val index: Int, val startPageFocusedState: Boolean) : SplitStudyReduce

        data object UpdateStartPageInputState : SplitStudyReduce

        data class UpdateEndPage(val index: Int,val endPage: String) : SplitStudyReduce

        data class UpdateEndPageFocusedState(val index: Int, val endPageFocusedState: Boolean) : SplitStudyReduce

        data object UpdateEndPageInputState : SplitStudyReduce

        data object UpdateExamDate : SplitStudyReduce

        data class UpdateSelectedDate(val date: Date) : SplitStudyReduce

        data object UpdateDatePickerBottomSheetState : SplitStudyReduce

        data object UpdateButtonEnabled : SplitStudyReduce

        data class UpdateStartPageToString(val index: Int) : SplitStudyReduce

        data class UpdateEndPageToString(val index: Int) : SplitStudyReduce

        data object UpdatePieceNumber: SplitStudyReduce

        data class UpdateSeletedIndex(val index: Int): SplitStudyReduce

        data object UpdateState: SplitStudyReduce
    }

    sealed interface SplitStudySideEffect : BaseContract.SideEffect {
        data object NavigateAddStudy : SplitStudySideEffect
    }
}