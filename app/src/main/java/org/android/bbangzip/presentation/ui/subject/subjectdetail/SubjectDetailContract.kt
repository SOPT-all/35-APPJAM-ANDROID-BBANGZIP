package org.android.bbangzip.presentation.ui.subject.subjectdetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.SubjectDetailInfo
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.PieceViewType
import org.android.bbangzip.presentation.util.base.BaseContract

class SubjectDetailContract {
    @Parcelize
    data class SubjectDetailState(
        val tabIndex: Int = 0,
        val isMenuOpen: Boolean = false,
        val isTopBarShadowed: Boolean = false,
        val pieceViewType: PieceViewType = PieceViewType.DEFAULT,
        val selectedItemSet: Set<Int> = setOf(),
        val revertCompleteBottomSheetState: Boolean = false,
        val examDate: String = "",
        val examDday : Int = 0,
        val motivationMessage : String = "",
        val selectedItemId: Int = -1,
        val subjectId : Int = 0,
        val todoList: List<ToDoCardModel> =
            listOf(
                ToDoCardModel(
                    pieceId = 1,
                    subjectName = "",
                    examName = "",
                    studyContents = "Review Chapters 1-3",
                    startPage = 1,
                    finishPage = 50,
                    deadline = "2025-01-20",
                    remainingDays = 2,
                    cardState = BbangZipCardState.COMPLETE,
                ),
                ToDoCardModel(
                    pieceId = 2,
                    subjectName = "",
                    examName = "",
                    studyContents = "Notes on WWII",
                    startPage = 5,
                    finishPage = 30,
                    deadline = "2025-01-22",
                    remainingDays = -3,
                    BbangZipCardState.DEFAULT,
                ),
                ToDoCardModel(
                    pieceId = 3,
                    subjectName = "",
                    examName = "",
                    studyContents = "Kinematics and Dynamics",
                    startPage = 20,
                    finishPage = 80,
                    deadline = "2025-01-25",
                    remainingDays = 7,
                    cardState = BbangZipCardState.COMPLETE,
                ),
                ToDoCardModel(
                    pieceId = 4,
                    subjectName = "",
                    examName = "",
                    studyContents = "Checkable abstract art piece",
                    startPage = 0,
                    finishPage = 0,
                    deadline = "2025-01-28",
                    remainingDays = -2,
                    cardState = BbangZipCardState.DEFAULT,
                ),
                ToDoCardModel(
                    pieceId = 5,
                    subjectName = "",
                    examName = "",
                    studyContents = "Notes on WWII",
                    startPage = 5,
                    finishPage = 30,
                    deadline = "2025-01-22",
                    remainingDays = 4,
                    BbangZipCardState.DEFAULT,
                ),
                ToDoCardModel(
                    pieceId = 6,
                    subjectName = "",
                    examName = "",
                    studyContents = "Kinematics and Dynamics",
                    startPage = 20,
                    finishPage = 80,
                    deadline = "2025-01-25",
                    remainingDays = -1,
                    BbangZipCardState.DEFAULT,
                ),
                ToDoCardModel(
                    pieceId = 7,
                    subjectName = "",
                    examName = "",
                    studyContents = "Checkable abstract art piece",
                    startPage = 0,
                    finishPage = 0,
                    deadline = "2025-01-28",
                    remainingDays = 5,
                    BbangZipCardState.DEFAULT,
                ),
                ToDoCardModel(
                    pieceId = 8,
                    subjectName = "",
                    examName = "",
                    studyContents = "Notes on WWII",
                    startPage = 5,
                    finishPage = 30,
                    deadline = "2025-01-22",
                    remainingDays = -4,
                    BbangZipCardState.DEFAULT,
                ),
                ToDoCardModel(
                    pieceId = 9,
                    subjectName = "",
                    examName = "",
                    studyContents = "Kinematics and Dynamics",
                    startPage = 20,
                    finishPage = 80,
                    deadline = "2025-01-25",
                    remainingDays = 3,
                    BbangZipCardState.DEFAULT,
                ),
                ToDoCardModel(
                    pieceId = 10,
                    subjectName = "",
                    examName = "",
                    studyContents = "CHECKABLE abstract art piece",
                    startPage = 0,
                    finishPage = 0,
                    deadline = "2025-01-28",
                    remainingDays = -5,
                    BbangZipCardState.DEFAULT,
                ),
            ),
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface SubjectDetailEvent : BaseContract.Event {
        data class Initialize(val subjectId:Int) : SubjectDetailEvent

        data object OnPlusIconClicked : SubjectDetailEvent

        data object OnTrashIconClicked : SubjectDetailEvent

        data object OnCloseIconClicked : SubjectDetailEvent

        data object OnDeleteButtonClicked : SubjectDetailEvent

        data class OnRevertCompleteBottomSheetApproveButtonClicked(val pieceId: Int) : SubjectDetailEvent

        data object OnRevertCompleteBottomSheetDismissButtonClicked : SubjectDetailEvent

        data object OnRevertCompleteBottomSheetDissmissRequest : SubjectDetailEvent

        data class OnDeleteModeCardClicked(
            val pieceId: Int,
        ) : SubjectDetailEvent

        data class OnDefaultCardClicked(
            val pieceId: Int,
        ) : SubjectDetailEvent

        data class OnCompleteCardClicked(
            val pieceId: Int,
        ) : SubjectDetailEvent
    }

    sealed interface SubjectDetailReduce : BaseContract.Reduce {
        data class UpdateSubjectDetail(val subjectDetailInfo: SubjectDetailInfo) : SubjectDetailReduce

        data object UpdateToDeleteMode : SubjectDetailReduce

        data object UpdateToDefaultMode : SubjectDetailReduce

        data class UpdateDeleteModeCardState(val pieceId: Int) : SubjectDetailReduce

        data class UpdateDeleteSet(val pieceId: Int) : SubjectDetailReduce

        data class UpdateDefaultCardState(val pieceId: Int) : SubjectDetailReduce

        data object UpdateCompleteCardState : SubjectDetailReduce

        data class DeleteSelectedItemSet(val pieceId: Int) : SubjectDetailReduce

        data class UpdateSubjectId (val subjectId: Int) : SubjectDetailReduce

        data object UpdateRevertCompleteBottomSheetState : SubjectDetailReduce

        data class UpdateSelectedId(val pieceId: Int) : SubjectDetailReduce
    }

    sealed interface SubjectDetailSideEffect : BaseContract.SideEffect {
        data object NavigateToAddSubject : SubjectDetailSideEffect

        data object NavigateToAddStudy : SubjectDetailSideEffect

        data object ShowDeleteSuccessSnackBar : SubjectDetailSideEffect
    }
}
