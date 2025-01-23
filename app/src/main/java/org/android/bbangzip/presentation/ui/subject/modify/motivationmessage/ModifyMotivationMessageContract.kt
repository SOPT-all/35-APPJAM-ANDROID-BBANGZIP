package org.android.bbangzip.presentation.ui.subject.modify.motivationmessage

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.BbangZipTextFieldInputState
import org.android.bbangzip.presentation.util.base.BaseContract

class ModifyMotivationMessageContract {
    @Parcelize
    data class ModifyMotivationMessageState(
        val motivationMessage: String = "",
        val isTextFieldFocused: Boolean = false,
        val isButtonEnable: Boolean = false,
        val motivationMessageTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface ModifyMotivationMessageEvent : BaseContract.Event {
        data class OnFocusTextField(val isTextFieldFocused: Boolean) : ModifyMotivationMessageEvent

        data class OnChangeMotivationMessage(val motivationMessage: String) : ModifyMotivationMessageEvent

        data object OnClickBackBtn : ModifyMotivationMessageEvent

        data object OnClickModifyBtn : ModifyMotivationMessageEvent

        data object OnClickDeleteBtn : ModifyMotivationMessageEvent

    }
    sealed interface ModifyMotivationMessageReduce : BaseContract.Reduce {
        data class UpdateMotivationMessage(val motivationMessage: String) : ModifyMotivationMessageReduce

        data object UpdateIsButtonEnabled : ModifyMotivationMessageReduce

        data object UpdateMotivationMessageInputState : ModifyMotivationMessageReduce

        data class UpdateIsTextFieldFocused(val isTextFieldFocused: Boolean) : ModifyMotivationMessageReduce

        data object ResetSubjectNamge : ModifyMotivationMessageReduce

    }

    sealed interface ModifyMotivationMessageSideEffect : BaseContract.SideEffect {
        data object NavigateSubjectDetail : ModifyMotivationMessageSideEffect

        data class ShowSnackBar(val message : String) : ModifyMotivationMessageSideEffect
    }
}
