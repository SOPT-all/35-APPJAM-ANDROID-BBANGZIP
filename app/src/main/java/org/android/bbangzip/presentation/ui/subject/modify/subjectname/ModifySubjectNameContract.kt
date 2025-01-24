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
        val subjectId: Int = 0,
        val subjectNameTextFieldState: BbangZipTextFieldInputState = BbangZipTextFieldInputState.Default
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface ModifySubjectNameEvent : BaseContract.Event {
        data class Initialize(val subjectId: Int, val subjectName: String) : ModifySubjectNameEvent

        data class OnFocusTextField(val isTextFieldFocused: Boolean) : ModifySubjectNameEvent

        data class OnChangeSubjectName(val subjectName: String) : ModifySubjectNameEvent

        data object OnClickBackBtn : ModifySubjectNameEvent

        data class OnClickModifyBtn(val subjectId: Int, val subjectName: String) : ModifySubjectNameEvent

        data object OnClickDeleteBtn : ModifySubjectNameEvent

    }
    sealed interface ModifySubjectNameReduce : BaseContract.Reduce {
        data class UpdateSubjectName(val subjectName: String) : ModifySubjectNameReduce

        data object UpdateIsButtonEnabled : ModifySubjectNameReduce

        data object UpdateSubjectNameInputState : ModifySubjectNameReduce

        data class UpdateIsTextFieldFocused(val isTextFieldFocused: Boolean) : ModifySubjectNameReduce

        data class UpdateSubjectData(val subjectId: Int, val subjectName: String) : ModifySubjectNameReduce

        data object ResetSubjectNamge : ModifySubjectNameReduce

    }

    sealed interface ModifySubjectNameSideEffect : BaseContract.SideEffect {
        data class NavigationSubjectDetail(
            val subjectId: Int,
            val subjectName: String) : ModifySubjectNameSideEffect

        // 과목명 수정 완료!
        data object ShowSnackBar : ModifySubjectNameSideEffect

        // 이미 등록된 과목이에요
        data object RedundantSnackBar : ModifySubjectNameSideEffect
    }
}
