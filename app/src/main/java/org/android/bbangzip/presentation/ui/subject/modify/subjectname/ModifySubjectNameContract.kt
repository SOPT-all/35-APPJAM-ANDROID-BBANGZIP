package org.android.bbangzip.presentation.ui.subject.modify.subjectname

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.util.base.BaseContract

class ModifySubjectNameContract {
    @Parcelize
    data class ModifySubjectNameState(
        val subjectName: String = "",
        val isTextFieldFocused: Boolean = false,
        val isButtonEnable: Boolean = false,
        val subjectNameTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface ModifySubjectNameEvent : BaseContract.Event {
        data class OnFocusTextField(val isTextFieldFocused: Boolean) : ModifySubjectNameEvent

        data class OnChangeSubjectName(val subjectName: String) : ModifySubjectNameEvent

        data object OnClickBackBtn : ModifySubjectNameEvent

        data object OnClickModifyBtn : ModifySubjectNameEvent

        data object OnClickDeleteBtn : ModifySubjectNameEvent

    }
    sealed interface ModifySubjectNameReduce : BaseContract.Reduce {
        data class UpdateSubjectName(val subjectName: String) : ModifySubjectNameReduce

        data object UpdateIsButtonEnabled : ModifySubjectNameReduce

        data object UpdateSubjectNameInputState : ModifySubjectNameReduce

        data class UpdateIsTextFieldFocused(val isTextFieldFocused: Boolean) : ModifySubjectNameReduce

        data object ResetSubjectNamge : ModifySubjectNameReduce

    }

    sealed interface ModifySubjectNameSideEffect : BaseContract.SideEffect {
        data object NavigationSubjectDetail : ModifySubjectNameSideEffect
    }
}
