package org.android.bbangzip.presentation.ui.subject.subjectdetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.card.SubjectCardModel
import org.android.bbangzip.presentation.type.CardViewType
import org.android.bbangzip.presentation.type.TodoViewType
import org.android.bbangzip.presentation.util.base.BaseContract

class SubjectDetailContract {
    @Parcelize
    data class SubjectDetailState(
        val tabIndex: Int = 0,
        val isMenuOpen: Boolean = false,
        val isTopBarShadowed: Boolean = false,
        val todoViewType: TodoViewType = TodoViewType.DEFAULT,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface SubjectEvent : BaseContract.Event {
        data object Initialize : SubjectEvent
    }

    sealed interface SubjectReduce : BaseContract.Reduce {

    }

    sealed interface SubjectSideEffect : BaseContract.SideEffect {
        data object NavigateToAddSubject : SubjectSideEffect

        data object NavigateToAddStudy : SubjectSideEffect

        data object ShowDeleteSuccessSnackBar : SubjectSideEffect
    }
}