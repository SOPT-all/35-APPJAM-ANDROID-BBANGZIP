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
        val isButtonEnabled: Boolean = false,
        val subjectId: Int = 0,
        val textFieldInputState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface ModifySubjectNameEvent : BaseContract.Event {
        data class Initialize(val subjectId: Int, val subjectName: String) : ModifySubjectNameEvent

        data class OnTextFieldFocusChange(val isTextFieldFocused: Boolean) : ModifySubjectNameEvent

        data class OnSubjectNameChange(val subjectName: String) : ModifySubjectNameEvent

        data object OnBackIconClick : ModifySubjectNameEvent

        data class OnModifyBtnClick(val subjectId: Int, val subjectName: String) : ModifySubjectNameEvent

        data object OnTextFieldDeleteIconClick : ModifySubjectNameEvent
    }

    sealed interface ModifySubjectNameReduce : BaseContract.Reduce {
        data class UpdateSubjectName(val subjectName: String) : ModifySubjectNameReduce

        data object UpdateIsButtonEnabled : ModifySubjectNameReduce

        data object UpdateSubjectNameInputState : ModifySubjectNameReduce

        data class UpdateIsTextFieldFocused(val isTextFieldFocused: Boolean) : ModifySubjectNameReduce

        data class UpdateSubjectData(val subjectId: Int, val subjectName: String) : ModifySubjectNameReduce

        data object ResetSubjectName : ModifySubjectNameReduce
    }

    sealed interface ModifySubjectNameSideEffect : BaseContract.SideEffect {
        data class NavigationSubjectDetail(
            val subjectId: Int,
            val subjectName: String,
        ) : ModifySubjectNameSideEffect

        // 과목명 수정 완료!
        data object ShowSnackBar : ModifySubjectNameSideEffect

        // 이미 등록된 과목이에요
        data object RedundantSnackBar : ModifySubjectNameSideEffect

        data object NavigateToBack: ModifySubjectNameSideEffect
    }
}
