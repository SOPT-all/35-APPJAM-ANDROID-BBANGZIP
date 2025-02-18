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
        val isButtonEnabled: Boolean = false,
        val subjectId: Int = 0,
        val subjectName: String = "",
        val motivationMessageTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface ModifyMotivationMessageEvent : BaseContract.Event {
        data class OnTextFieldFocusChange(val isTextFieldFocused: Boolean) : ModifyMotivationMessageEvent

        data class OnMotivationMessageChange(val motivationMessage: String) : ModifyMotivationMessageEvent

        data object OnBackIconClick : ModifyMotivationMessageEvent

        data class OnModifyBtnClick(val subjectId: Int, val subjectName: String) : ModifyMotivationMessageEvent

        data object OnTextFieldDeleteIconClick : ModifyMotivationMessageEvent

        data class Initialize(val subjectId: Int, val subjectName: String) : ModifyMotivationMessageEvent
    }

    sealed interface ModifyMotivationMessageReduce : BaseContract.Reduce {
        data class UpdateMotivationMessage(val motivationMessage: String) : ModifyMotivationMessageReduce

        data object UpdateIsButtonEnabled : ModifyMotivationMessageReduce

        data object UpdateMotivationMessageInputState : ModifyMotivationMessageReduce

        data class UpdateIsTextFieldFocused(val isTextFieldFocused: Boolean) : ModifyMotivationMessageReduce

        data object ResetSubjectNamge : ModifyMotivationMessageReduce

        data class UpdateSubjectInfo(val subjectId: Int, val subjectName: String) : ModifyMotivationMessageReduce
    }

    sealed interface ModifyMotivationMessageSideEffect : BaseContract.SideEffect {
        data class NavigateSubjectDetail(val subjectId: Int, val subjectName: String) : ModifyMotivationMessageSideEffect

        data class ShowSnackBar(val message: String) : ModifyMotivationMessageSideEffect

        data object PopBackStack: ModifyMotivationMessageSideEffect
    }
}
