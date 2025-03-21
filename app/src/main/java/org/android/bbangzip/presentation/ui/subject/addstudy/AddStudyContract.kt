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
        val isStudyContentFocused: Boolean = false,
        val isStartPageFocused: Boolean = false,
        val isEndPageFocused: Boolean = false,
        val isEnrollBtnEnabled: Boolean = false,
        val isSplitBtnEnabled: Boolean = false,
        val isDatePickerBottomSheetVisible: Boolean = false,
        val isPiecePickerBottomSheetVisible: Boolean = false,
        val isGetBadgeBottomSheetVisible: Boolean = false,
        val isDatePickerEnabled: Boolean = true,
        val isSuccess: Boolean = false,
        val subjectId: Int = 0,
        val pieceNumber: Int = 0,
        val subjectName: String = "",
        val examDate: String = "시험 일자 입력",
        val examName: String = "",
        val studyContent: String? = null,
        val startPage: String? = null,
        val endPage: String? = null,
        val startPageGuideline: String = "부터",
        val endPageGuideline: String = "까지",
        val selectedDate: Date = Date("2025", "1", "21"),
        val studyContentTextFieldInputState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
        val startPageTextFieldInputState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
        val endPageTextFieldInputState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
        val addStudyViewType: AddStudyViewType = AddStudyViewType.DEFAULT,
        val startPageList: List<String> = emptyList(),
        val endPageList: List<String> = emptyList(),
        val deadLineList: List<String> = emptyList(),
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface AddStudyEvent : BaseContract.Event {
        data class Initialize(val splitStudyData: SplitStudyData) : AddStudyEvent

        data class OnStudyContentChange(val studyContent: String) : AddStudyEvent

        data class OnStartPageChange(val startPage: String) : AddStudyEvent

        data class OnEndPageChange(val endPage: String) : AddStudyEvent

        data class OnStudyContentFocusChange(val isStudyContentFocused: Boolean) : AddStudyEvent

        data class OnStartPageFocusChange(val isStartPageFocused: Boolean) : AddStudyEvent

        data class OnEndPageFocusChange(val isEndPageFocused: Boolean) : AddStudyEvent

        data class OnSelectedDateChange(val selectedDate: Date) : AddStudyEvent

        data object OnDatePickerClick : AddStudyEvent

        data class OnPieceNumberClick(val pieceNumber: Int) : AddStudyEvent

        data object OnBackIconClick : AddStudyEvent

        data object OnCancleIconClick : AddStudyEvent

        data object OnSplitBtnClick : AddStudyEvent

        data object OnNextBtnClick : AddStudyEvent

        data object OnEnrollBtnClick : AddStudyEvent

        data object OnConfirmDateBtnClick : AddStudyEvent

        data class OnReSplitBtnClick(val pieceNumber: Int) : AddStudyEvent

        data object OnAddStudyBtnClick : AddStudyEvent

        data object OnDirectEnrollBtnClick : AddStudyEvent
    }

    sealed interface AddStudyReduce : BaseContract.Reduce {
        data class Initialize(val splitStudyData: SplitStudyData) : AddStudyReduce

        data class UpdateStudyContent(val studyContent: String) : AddStudyReduce

        data class UpdateIsStudyContentFocused(val studyContentFocusedState: Boolean) : AddStudyReduce

        data object UpdateStudyContentInputState : AddStudyReduce

        data class UpdateStartPage(val startPage: String) : AddStudyReduce

        data class UpdateIsStartPageFocused(val startPageFocusedState: Boolean) : AddStudyReduce

        data object UpdateStartPageInputState : AddStudyReduce

        data class UpdateEndPage(val endPage: String) : AddStudyReduce

        data class UpdateIsEndPageFocused(val endPageFocusedState: Boolean) : AddStudyReduce

        data object UpdateEndPageInputState : AddStudyReduce

        data object UpdateExamDate : AddStudyReduce

        data class UpdateSelectedDate(val date: Date) : AddStudyReduce

        data object UpdateIsDatePickerBottomSheetVisible : AddStudyReduce

        data object UpdateIsPiecePickerBottomSheetVisible : AddStudyReduce

        data class UpdatePieceNumber(val pieceNumber: Int) : AddStudyReduce

        data object UpdateIsEnrollBtnEnabled : AddStudyReduce

        data object UpdateIsSplitBtnEnabled : AddStudyReduce

        data object AddSuffixToStartPage : AddStudyReduce

        data object AddSuffixToEndPage : AddStudyReduce

        data object UpdateAddStudyViewType : AddStudyReduce

        data object UpdateIsSuccess : AddStudyReduce

        data object UpdateStartPageGuideLine : AddStudyReduce

        data object UpdateEndPageGuideLine : AddStudyReduce

        data object UpdatePieceList : AddStudyReduce

        data object ResetStudyContent : AddStudyReduce
    }

    sealed interface AddStudySideEffect : BaseContract.SideEffect {
        data class NavigateSplitStudy(val addStudyData: AddStudyData) : AddStudySideEffect

        data class NavigateSubjectDetail(val subjectId: Int, val subjectName: String) : AddStudySideEffect

        data object PopBackStack : AddStudySideEffect

        data class ShowSnackBar(val message: String) : AddStudySideEffect
    }
}
