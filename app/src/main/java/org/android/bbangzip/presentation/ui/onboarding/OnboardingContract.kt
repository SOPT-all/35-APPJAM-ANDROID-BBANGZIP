package org.android.bbangzip.presentation.ui.onboarding

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
        val buttonEnabled: Boolean = true,
        val userNameTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
        val userNameFocusedState: Boolean = false,
        val semesterPickerState: Boolean = true,
        val subjectNameTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
        val subjectNameFocusedState: Boolean = false,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface OnboardingEvent : BaseContract.Event {
        data class OnChangeUserName(val userName: String) : OnboardingEvent

        data class OnChangeUserNameFocused(val isFocused: Boolean) : OnboardingEvent

        data class OnChangeSemester(val semester: Semester) : OnboardingEvent

        data class OnChangeSubject(val subject: String) : OnboardingEvent

        data class OnChangeSubjectFocused(val isFocused: Boolean) : OnboardingEvent

        data class OnChangeCurrentPage(val currentPage: Int) : OnboardingEvent

        data object OnClickDeleteUserName : OnboardingEvent

        data object OnClickDeleteSubject : OnboardingEvent

        data object OnClickBackBtn : OnboardingEvent

        data object OnClickNextBtn : OnboardingEvent

        data object OnClickFinishBtn : OnboardingEvent
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
        data object PopBackStack : OnboardingSideEffect

        data object NavigateToOnboardingStart : OnboardingSideEffect

        data object NavigateToOnboarding : OnboardingSideEffect

        data object NavigateToOnboardingEnd : OnboardingSideEffect

        data object NavigateToSubject : OnboardingSideEffect
    }
}
