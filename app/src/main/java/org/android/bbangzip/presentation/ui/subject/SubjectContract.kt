package org.android.bbangzip.presentation.ui.subject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.card.SubjectCardModel
import org.android.bbangzip.presentation.type.CardViewType
import org.android.bbangzip.presentation.util.base.BaseContract

class SubjectContract {
    @Parcelize
    data class SubjectState(
        val subjectCardList: List<SubjectCardModel> = listOf(),
        val subjectIdSetToDelete: Set<Int> = setOf(),
        val subjectCardViewType: CardViewType = CardViewType.DEFAULT,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface SubjectEvent : BaseContract.Event {
        data object Initialize : SubjectEvent

        data object OnTrashIconClick : SubjectEvent

        data object OnCancleIconClick : SubjectEvent

        data class OnDefaultModeSubjectCardClick(
            val subjectId: Int,
            val subjectName: String,
        ) : SubjectEvent

        data object OnAddSubjectCardClick : SubjectEvent

        data class OnDeleteModeSubjectCardClick(val subjectId: Int) : SubjectEvent

        data object OnDeleteButtonClick : SubjectEvent
    }

    sealed interface SubjectReduce : BaseContract.Reduce {
        data object UpdateToDeleteMode : SubjectReduce

        data object UpdateToDefaultMode : SubjectReduce

        data class UpdateSubjectCard(val subjectId: Int) : SubjectReduce

        data class UpdateSubjectCardList(val subjectList: List<SubjectCardModel>) : SubjectReduce

        data class UpdateDeletedSet(val subjectId: Int) : SubjectReduce

        data object ResetSubjectIdSetToDelete : SubjectReduce
    }

    sealed interface SubjectSideEffect : BaseContract.SideEffect {
        data object NavigateToAddSubject : SubjectSideEffect

        data class NavigateToSubjectDetail(
            val subjectId: Int,
            val subjectName: String,
        ) : SubjectSideEffect

        data object ShowSnackbar : SubjectSideEffect
    }
}
