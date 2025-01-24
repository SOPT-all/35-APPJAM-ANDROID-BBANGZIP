package org.android.bbangzip.presentation.ui.subject.addstudy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.AddStudyData
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.model.Date
import org.android.bbangzip.presentation.model.SplitStudyData
import org.android.bbangzip.presentation.type.AddStudyViewType
import org.android.bbangzip.presentation.util.base.BaseContract

class AddStudyContract {
    @Parcelize
    data class AddStudyState(
        val subjectName: String = "",
        val examDate: String = "시험 일자 입력",
        val examName: String = "",
        val selectedDate: Date = Date("2025", "1", "21"),
        val studyContent: String? = null,
        val studyContentTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
        val studyContentFocusedState: Boolean = false,
        val startPage: String? = null,
        val startPageTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
        val startPageFocusedState: Boolean = false,
        val startPageGuideline: String = "부터",
        val endPageGuideline: String = "까지",
        val startPageList: List<String> = emptyList(),
        val endPage: String? = null,
        val endPageTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
        val endPageFocusedState: Boolean = false,
        val endPageList: List<String> = emptyList(),
        val buttonEnabled: Boolean = false,
        val buttonSplitEnabled: Boolean = false,
        val datePickerBottomSheetState: Boolean = false,
        val piecePickerBottomSheetState: Boolean = false,
        val pieceNumber: Int = 0,
        val deadLineList: List<String> = emptyList(),
        val addStudyViewType: AddStudyViewType = AddStudyViewType.DEFAULT,
        val isSuccess: Boolean = false,
        val getBadgeBottomSheetState: Boolean = false
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface AddStudyEvent : BaseContract.Event {
        data class Initialize(val splitStudyData: SplitStudyData) : AddStudyEvent

        data class OnChangeStudyContent(val studyContent: String) : AddStudyEvent

        data class OnChangeStartPage(val startPage: String) : AddStudyEvent

        data class OnChangeEndPage(val endPage: String) : AddStudyEvent

        data class OnChangeStudyContentFocused(val studyContentFocusedState: Boolean) : AddStudyEvent

        data class OnChangeStartPageFocused(val startPageFocusedState: Boolean) : AddStudyEvent

        data class OnChangeEndPageFocused(val endPageFocusedState: Boolean) : AddStudyEvent

        data class OnChangeSelectedDate(val selectedDate: Date) : AddStudyEvent

        data object OnClickDatePicker : AddStudyEvent

        data class OnClickPieceNumber(val pieceNumber: Int) : AddStudyEvent

        data object OnClickBackIcon : AddStudyEvent

        data object OnClickCancleIcon : AddStudyEvent

        data object OnClickSplitBtn : AddStudyEvent

        data object OnClickNextBtn : AddStudyEvent

        data object OnClickEnrollBtn : AddStudyEvent

        data object OnClickConfirmDateBtn : AddStudyEvent

        data class OnClickAgainSplitBtn(val pieceNumber: Int) : AddStudyEvent

        data object OnClickAddStudyBtn : AddStudyEvent
    }

    sealed interface AddStudyReduce : BaseContract.Reduce {
        data class Initialize(val splitStudyData: SplitStudyData) : AddStudyReduce

        data class UpdateStudyContent(val studyContent: String) : AddStudyReduce

        data class UpdateStudyContentFocusedState(val studyContentFocusedState: Boolean) : AddStudyReduce

        data object UpdateStudyContentInputState : AddStudyReduce

        data class UpdateStartPage(val startPage: String) : AddStudyReduce

        data class UpdateStartPageFocusedState(val startPageFocusedState: Boolean) : AddStudyReduce

        data object UpdateStartPageInputState : AddStudyReduce

        data class UpdateEndPage(val endPage: String) : AddStudyReduce

        data class UpdateEndPageFocusedState(val endPageFocusedState: Boolean) : AddStudyReduce

        data object UpdateEndPageInputState : AddStudyReduce

        data object UpdateExamDate : AddStudyReduce

        data class UpdateSelectedDate(val date: Date) : AddStudyReduce

        data object UpdateDatePickerBottomSheetState : AddStudyReduce

        data object UpdatePiecePickerBottomSheetState : AddStudyReduce

        data class UpdatePieceNumber(val pieceNumber: Int) : AddStudyReduce

        data object UpdateButtonEnabled : AddStudyReduce

        data object UpdateSplitButtonEnabled : AddStudyReduce

        data object UpdateStartPageToString : AddStudyReduce

        data object UpdateEndPageToString : AddStudyReduce

        data object UpdateAddStudyViewType : AddStudyReduce

        data object UpdateIsSuccess : AddStudyReduce

        data object UpdateStartPageGuideline : AddStudyReduce

        data object UpdateEndPageGuideLine : AddStudyReduce

        data object ResetStudyContent : AddStudyReduce
    }

    sealed interface AddStudySideEffect : BaseContract.SideEffect {
        data class NavigateSplitStudy(val addStudyData: AddStudyData) : AddStudySideEffect

        data object PopBackStack : AddStudySideEffect
    }
}
