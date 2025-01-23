package org.android.bbangzip.presentation.ui.subject.addsubject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.util.base.BaseContract

class AddSubjectContract {
    @Parcelize
    data class AddSubjectState(
        val subjectName: String = "",
        val isTextFieldFocused: Boolean = false,
        val isButtonEnable: Boolean = false,
        val subjectTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface AddSubjectEvent : BaseContract.Event {
        data class OnFocusTextField(val isTextFieldFocused: Boolean) : AddSubjectEvent

        data class OnChangeSubjectName(val subjectName: String) : AddSubjectEvent

        data object OnClickBackBtn : AddSubjectEvent

        data object OnClickAddBtn : AddSubjectEvent

        data object OnClickDeleteBtn : AddSubjectEvent
    }
    sealed interface AddSubjectReduce : BaseContract.Reduce {
        data class UpdateSubjectName(val subjectName: String) : AddSubjectReduce

        data object UpdateIsButtonEnabled : AddSubjectReduce

        data object UpdateSubjectInputState : AddSubjectReduce

        data class UpdateIsTextFieldFocused(val isTextFieldFocused: Boolean) : AddSubjectReduce

        data object ResetSubjectName : AddSubjectReduce

    }

    sealed interface AddSubjectSideEffect : BaseContract.SideEffect {
        data object NavigationSubject : AddSubjectSideEffect
    }
}
