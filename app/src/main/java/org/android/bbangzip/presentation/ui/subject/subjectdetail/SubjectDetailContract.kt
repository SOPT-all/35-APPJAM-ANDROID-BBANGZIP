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
        val isMenuOpen: Boolean = false,
        val isTopBarShadowed: Boolean = false,
        val isRevertCompleteBottomSheetVisible: Boolean = false,
        val isGetBadgeBottomSheetVisible: Boolean = false,
        val pieceViewType: PieceViewType = PieceViewType.DEFAULT,
        val tabIndex: Int = 0,
        val examDate: String = "2025년 11월 25일",
        val examDDay: Int = -14,
        val motivationMessage: String = "사장님의 각오 한마디를 작성해보세요",
        val selectedPieceId: Int = -1,
        val subjectId: Int = 0,
        val examName: String = "중간고사",
        val subjectName: String = "",
        val selectedPieceSet: Set<Int> = setOf(),
        val todoList: List<ToDoCardModel> = emptyList(),
        val badgeList: List<Badge> = emptyList(),
    ) : BaseContract.State, Parcelable {
        override fun toParcelable(): Parcelable = this
    }

    sealed interface SubjectDetailEvent : BaseContract.Event {
        data class Initialize(
            val subjectId: Int,
            val subjectName: String,
        ) : SubjectDetailEvent

        data class OnPlusIconClick(val splitStudyData: SplitStudyData) : SubjectDetailEvent

        data class OnAddStudyBtnClick(val splitStudyData: SplitStudyData) : SubjectDetailEvent

        data class OnAddStudyCardClick(val splitStudyData: SplitStudyData) : SubjectDetailEvent

        data object OnTrashIconClick : SubjectDetailEvent

        data object OnCloseIconClick : SubjectDetailEvent

        data object OnDeleteBtnClick : SubjectDetailEvent

        data class OnRevertCompleteBottomSheetApproveBtnClick(val pieceId: Int) : SubjectDetailEvent

        data object OnRevertCompleteBottomSheetDismissBtnClick : SubjectDetailEvent

        data object OnRevertCompleteBottomSheetDismissRequest : SubjectDetailEvent

        data object OnKebabIconClick : SubjectDetailEvent

        data class OnTabClick(val index: Int) : SubjectDetailEvent

        data class OnEnrollMotivateMessageClick(
            val subjectId: Int,
            val subjectName: String,
        ) : SubjectDetailEvent

        data class OnModifySubjectNameClick(
            val subjectId: Int,
            val subjectName: String,
        ) : SubjectDetailEvent

        data class OnDeleteModePieceCardClick(
            val pieceId: Int,
        ) : SubjectDetailEvent

        data class OnDefaultModePieceCardClick(
            val pieceId: Int,
        ) : SubjectDetailEvent

        data class OnCompleteModePieceCardClick(
            val pieceId: Int,
        ) : SubjectDetailEvent

        data object OnGetBadgeBottomSheetCloseBtnClick : SubjectDetailEvent

        data object OnBackIconBtnClick : SubjectDetailEvent

        data object OnMenuDismissRequest : SubjectDetailEvent
    }

    sealed interface SubjectDetailReduce : BaseContract.Reduce {
        data class UpdateSubjectDetail(val subjectDetailInfo: SubjectDetailInfo) : SubjectDetailReduce

        data object UpdateToDeleteMode : SubjectDetailReduce

        data object UpdateToDefaultMode : SubjectDetailReduce

        data class UpdateDeleteModeCardState(val pieceId: Int) : SubjectDetailReduce

        data class UpdateDefaultModeCardState(val pieceId: Int) : SubjectDetailReduce

        data object UpdateCompleteModeCardState : SubjectDetailReduce

        data class UpdateDeleteSet(val pieceId: Int) : SubjectDetailReduce

        data class UpdateSubjectData(
            val subjectId: Int,
            val subjectName: String,
        ) : SubjectDetailReduce

        data object UpdateIsRevertCompleteBottomSheetVisible : SubjectDetailReduce

        data class UpdateSelectedPieceId(val pieceId: Int) : SubjectDetailReduce

        data object UpdateIsMenuOpen : SubjectDetailReduce

        data class UpdateExamName(val index: Int) : SubjectDetailReduce

        data class UpdateGetBadgeList(val badgeList: List<Badge>) : SubjectDetailReduce

        data class UpdateIsGetBadgeBottomSheetVisible(val getBadgeBottomSheetState: Boolean) : SubjectDetailReduce

        data object UpdateToEmptyView : SubjectDetailReduce

        data class DeleteStudyPiece(val studyPieceId: Set<Int>) : SubjectDetailReduce
    }

    sealed interface SubjectDetailSideEffect : BaseContract.SideEffect {
        data class NavigateToAddStudy(val splitStudyData: SplitStudyData) : SubjectDetailSideEffect

        data class NavigateToModifyMotivation(val subjectId: Int, val subjectName: String) : SubjectDetailSideEffect

        data class NavigateToModifySubjectName(val subjectId: Int, val subjectName: String) : SubjectDetailSideEffect

        data object ShowSnackBar : SubjectDetailSideEffect

        data object NavigateToBack : SubjectDetailSideEffect
    }
}
