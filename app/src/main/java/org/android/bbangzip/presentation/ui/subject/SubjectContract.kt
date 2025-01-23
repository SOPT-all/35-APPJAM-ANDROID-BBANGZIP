package org.android.bbangzip.presentation.ui.subject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.card.SubjectCardModel
import org.android.bbangzip.presentation.type.CardViewType
import org.android.bbangzip.presentation.util.base.BaseContract

class SubjectContract {
    @Parcelize
    data class SubjectState(
        val subjectList: List<SubjectCardModel> = listOf(),
        val subjectSetToDelete: Set<Int> = setOf(),
        val cardViewType: CardViewType = CardViewType.DEFAULT,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface SubjectEvent : BaseContract.Event {
        data object Initialize : SubjectEvent

        data object OnClickTrashIcon : SubjectEvent

        data object OnClickCancleIcon : SubjectEvent

        data class OnClickStudyCard(val subjectId: Int) : SubjectEvent

        data class OnClickDeleteModeCard(val subjectId: Int) : SubjectEvent

        data class OnClickDeleteButton(val subjectId: Int) : SubjectEvent
    }

    sealed interface SubjectReduce : BaseContract.Reduce {
        data object UpdateToDeleteMode : SubjectReduce

        data object UpdateToDefaultMode : SubjectReduce

        data class UpdateSubjectCard(val subjectId: Int) : SubjectReduce

        data class UpdateSubjectCardList(val subjectList: List<SubjectCardModel>) : SubjectReduce

        data class UpdateDeletedSet(val subjectId: Int) : SubjectReduce
    }

    sealed interface SubjectSideEffect : BaseContract.SideEffect {
        data object NavigateToAddSubject : SubjectSideEffect

        data object NavigateToAddStudy : SubjectSideEffect

        data class NavigateToSubjectDetail(val subjectId: Int) : SubjectSideEffect

        data object ShowDeleteSuccessSnackBar : SubjectSideEffect
    }
}
