package org.android.bbangzip.presentation.ui.subject.subjectdetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.android.bbangzip.presentation.model.Badge
import org.android.bbangzip.presentation.model.SplitStudyData
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
        val examDate: String = "2025년 11월 25일",
        val examDday: Int = -14,
        val motivationMessage: String = "사장님의 각오 한마디를 작성해보세요",
        val selectedItemId: Int = -1,
        val subjectId: Int = 0,
        val examName: String = "중간고사",
        val subjectName: String = "",
        val todoList: List<ToDoCardModel> = emptyList(),
        val badgeList: List<Badge> = emptyList(),
        val getBadgeBottomSheetState: Boolean = false,
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface SubjectDetailEvent : BaseContract.Event {
        data class Initialize(
            val subjectId: Int,
            val subjectName: String,
        ) : SubjectDetailEvent

        data class OnPlusIconClicked(val splitStudyData: SplitStudyData) : SubjectDetailEvent

        data object OnTrashIconClicked : SubjectDetailEvent

        data object OnCloseIconClicked : SubjectDetailEvent

        data object OnDeleteButtonClicked : SubjectDetailEvent

        data class OnRevertCompleteBottomSheetApproveButtonClicked(val pieceId: Int) : SubjectDetailEvent

        data object OnRevertCompleteBottomSheetDismissButtonClicked : SubjectDetailEvent

        data object OnRevertCompleteBottomSheetDissmissRequest : SubjectDetailEvent

        data object OnClickKebabMenu : SubjectDetailEvent

        data class OnClickTab(val index: Int) : SubjectDetailEvent

        data class OnClickEnrollMotivateMessage(
            val subjectId: Int,
            val subjectName: String,
        ) : SubjectDetailEvent

        data class OnClickModifySubjectName(
            val subjectId: Int,
            val subjectName: String,
        ) : SubjectDetailEvent

        data class OnDeleteModeCardClicked(
            val pieceId: Int,
        ) : SubjectDetailEvent

        data class OnDefaultCardClicked(
            val pieceId: Int,
        ) : SubjectDetailEvent

        data class OnCompleteCardClicked(
            val pieceId: Int,
        ) : SubjectDetailEvent

        data object OnClickGetBadgeBottomSheetCloseBtn : SubjectDetailEvent

        data object OnClickBackIconBtn : SubjectDetailEvent

        data object OnClickKebabOutside : SubjectDetailEvent
    }

    sealed interface SubjectDetailReduce : BaseContract.Reduce {
        data class UpdateSubjectDetail(val subjectDetailInfo: SubjectDetailInfo) : SubjectDetailReduce

        data object UpdateIsKebabMenuOpen : SubjectDetailReduce

        data object UpdateToDeleteMode : SubjectDetailReduce

        data object UpdateToDefaultMode : SubjectDetailReduce

        data class UpdateDeleteModeCardState(val pieceId: Int) : SubjectDetailReduce

        data class UpdateDeleteSet(val pieceId: Int) : SubjectDetailReduce

        data class UpdateDefaultCardState(val pieceId: Int) : SubjectDetailReduce

        data object UpdateCompleteCardState : SubjectDetailReduce

        data class DeleteSelectedItemSet(val pieceId: Int) : SubjectDetailReduce

        data class UpdateSubjectData(
            val subjectId: Int,
            val subjectName: String,
        ) : SubjectDetailReduce

        data object UpdateRevertCompleteBottomSheetState : SubjectDetailReduce

        data class UpdateSelectedId(val pieceId: Int) : SubjectDetailReduce

        data object UpdateIsMenuOpen : SubjectDetailReduce

        data class UpdateExamName(val index: Int) : SubjectDetailReduce

        data class UpdateGetBadgeList(val badgeList: List<Badge>) : SubjectDetailReduce

        data class UpdateGetBadgeBottomSheetState(val getBadgeBottomSheetState: Boolean) : SubjectDetailReduce

        data object UpdateToEmptyView : SubjectDetailReduce

        data class DeleteStudyPiece(val studyPieceId: Set<Int>) : SubjectDetailReduce
    }

    sealed interface SubjectDetailSideEffect : BaseContract.SideEffect {
        data object NavigateToAddSubject : SubjectDetailSideEffect

        data class NavigateToAddStudy(val splitStudyData: SplitStudyData) : SubjectDetailSideEffect

        data class NavigateToModifyMotivation(val subjectId: Int, val subjectName: String) : SubjectDetailSideEffect

        data class NavigateToModifySubjectName(val subjectId: Int, val subjectName: String) : SubjectDetailSideEffect

        // 공부 n개가 삭제 되었어요
        data object ShowDeleteSuccessSnackBar : SubjectDetailSideEffect

        data object PopBackStack : SubjectDetailSideEffect
    }
}
