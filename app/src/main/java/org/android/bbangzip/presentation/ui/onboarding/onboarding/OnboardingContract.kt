package org.android.bbangzip.presentation.ui.onboarding.onboarding

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.model.Semester
import org.android.bbangzip.presentation.type.SemesterType
import org.android.bbangzip.presentation.util.base.BaseContract
import org.android.bbangzip.presentation.util.constant.DateConstants.YEAR_OF_TODAY

class OnboardingContract {
    @Parcelize
    data class OnboardingState(
        val currentPage: Int = 0,
        val userName: String? = null,
        val semester: Semester = Semester(YEAR_OF_TODAY.toString(), SemesterType.FIRST),
        val subjectName: String? = null,
        val buttonEnabled: Boolean = false,
        val userNameTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
        val userNameFocusedState: Boolean = false,
        val semesterPickerState: Boolean = true,
        val subjectNameTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
        val subjectNameFocusedState: Boolean = false,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface OnboardingEvent : BaseContract.Event {
        data object Initialize : OnboardingEvent

        data class OnUserNameChange(val userName: String) : OnboardingEvent

        data class OnUserNameFocusChange(val isFocused: Boolean) : OnboardingEvent

        data class OnSemesterChange(val semester: Semester) : OnboardingEvent

        data class OnSubjectChange(val subject: String) : OnboardingEvent

        data class OnSubjectFocusChange(val isFocused: Boolean) : OnboardingEvent

        data class OnCurrentPageChange(val currentPage: Int) : OnboardingEvent

        data object OnUserNameDeleteBtnClick : OnboardingEvent

        data object OnSubjectDeleteBtnClick : OnboardingEvent

        data object OnBackBtnClick : OnboardingEvent

        data object OnNextBtnClick : OnboardingEvent
    }

    sealed interface OnboardingReduce : BaseContract.Reduce {
        data class UpdateState(val state: OnboardingState) : OnboardingReduce

        data class UpdateUserName(val userName: String) : OnboardingReduce

        data class UpdateUserNameFocused(val isFocused: Boolean) : OnboardingReduce

        data object DeleteUserName : OnboardingReduce

        data class UpdateSemester(val semester: Semester) : OnboardingReduce

        data class UpdateSubject(val subject: String) : OnboardingReduce

        data class UpdateSubjectFocused(val isFocused: Boolean) : OnboardingReduce

        data object DeleteSubject : OnboardingReduce

        data class UpdateCurrentPage(val nextPage: Int) : OnboardingReduce

        data class UpdateUserNameTextFieldSate(val userName: String) : OnboardingReduce

        data class UpdateSubjectNameTextFieldSate(val subject: String) : OnboardingReduce

        data object UpdateButtonEnabled : OnboardingReduce
    }

    sealed interface OnboardingSideEffect : BaseContract.SideEffect {
        data object NavigateToBack : OnboardingSideEffect

        data object NavigateToOnboardingEnd : OnboardingSideEffect
    }
}
