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
        val isButtonEnabled: Boolean = false,
        val subjectTextFieldInputState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface AddSubjectEvent : BaseContract.Event {
        data class OnTextFieldFocus(val isTextFieldFocused: Boolean) : AddSubjectEvent

        data class OnSubjectNameChange(val subjectName: String) : AddSubjectEvent

        data object OnAddBtnClick : AddSubjectEvent

        data object OnBackIconClick : AddSubjectEvent

        data object OnTextFieldDeleteIconClick : AddSubjectEvent
    }

    sealed interface AddSubjectReduce : BaseContract.Reduce {
        data object UpdateIsButtonEnabled : AddSubjectReduce

        data object UpdateSubjectInputState : AddSubjectReduce

        data class UpdateSubjectName(val subjectName: String) : AddSubjectReduce

        data class UpdateIsTextFieldFocused(val isTextFieldFocused: Boolean) : AddSubjectReduce

        data object ResetSubjectName : AddSubjectReduce
    }

    sealed interface AddSubjectSideEffect : BaseContract.SideEffect {
        data object NavigateToSubject : AddSubjectSideEffect

        data object NavigateToBack : AddSubjectSideEffect

        data class ShowSnackbar(val message: Int) : AddSubjectSideEffect
    }
}
