package org.android.bbangzip.presentation.ui.subject.subjectdetail

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.domain.usecase.GetSubjectDetailUseCase
import org.android.bbangzip.domain.usecase.PostCompleteCardIdUseCase
import org.android.bbangzip.domain.usecase.PostUnCompleteCardIdUseCase
import org.android.bbangzip.presentation.component.card.BbangZipCardState
import org.android.bbangzip.presentation.model.SubjectDetailInfo
import org.android.bbangzip.presentation.model.card.ToDoCardModel
import org.android.bbangzip.presentation.type.PieceViewType
import org.android.bbangzip.presentation.util.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SubjectDetailViewModel
    @Inject
    constructor(
        private val postCompleteCardIdUseCase: PostCompleteCardIdUseCase,
        private val postUnCompleteCardIdUseCase: PostUnCompleteCardIdUseCase,
        private val getSubjectDetailUseCase: GetSubjectDetailUseCase,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<SubjectDetailContract.SubjectDetailEvent, SubjectDetailContract.SubjectDetailState, SubjectDetailContract.SubjectDetailReduce, SubjectDetailContract.SubjectDetailSideEffect>(
            savedStateHandle = savedStateHandle,
        ) {
        override fun createInitialState(savedState: Parcelable?): SubjectDetailContract.SubjectDetailState {
            return savedState as? SubjectDetailContract.SubjectDetailState ?: SubjectDetailContract.SubjectDetailState()
        }

        override fun handleEvent(event: SubjectDetailContract.SubjectDetailEvent) {
            when (event) {
                is SubjectDetailContract.SubjectDetailEvent.Initialize ->
                    launch {
                        updateState(SubjectDetailContract.SubjectDetailReduce.UpdateSubjectData(event.subjectId, event.subjectName))
                        initData(event.subjectId)
                    }

                is SubjectDetailContract.SubjectDetailEvent.OnTrashIconClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateToDeleteMode)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnCloseIconClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateToDefaultMode)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnDeleteModeCardClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateDeleteModeCardState(event.pieceId))
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateDeleteSet(event.pieceId))
                }

                is SubjectDetailContract.SubjectDetailEvent.OnDefaultCardClicked -> {
                    viewModelScope.launch {
                        postCompleteCardId(event.pieceId)
                    }
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateDefaultCardState(event.pieceId))
                    // 사이드 이펙트  스낵바 메시지
                }

                is SubjectDetailContract.SubjectDetailEvent.OnCompleteCardClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateSelectedId(event.pieceId))
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateRevertCompleteBottomSheetState)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetApproveButtonClicked -> {
                    viewModelScope.launch {
                        postUnCompleteCardId(event.pieceId)
                    }
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateCompleteCardState)
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateRevertCompleteBottomSheetState)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDismissButtonClicked -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateRevertCompleteBottomSheetState)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDissmissRequest -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateRevertCompleteBottomSheetState)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnClickEnrollMotivateMessage -> {
                    setSideEffect(SubjectDetailContract.SubjectDetailSideEffect.NavigateToModifyMotivation(subjectId = event.subjectId, subjectName = event.subjectName))
                }

                is SubjectDetailContract.SubjectDetailEvent.OnClickModifySubjectName -> {
                    setSideEffect(SubjectDetailContract.SubjectDetailSideEffect.NavigateToModifySubjectName(subjectId = event.subjectId, subjectName = event.subjectName))
                }
                SubjectDetailContract.SubjectDetailEvent.OnDeleteButtonClicked -> {}
                is SubjectDetailContract.SubjectDetailEvent.OnPlusIconClicked -> {
                    Timber.tag("김재민").d("되나?")
                    setSideEffect(SubjectDetailContract.SubjectDetailSideEffect.NavigateToAddStudy(splitStudyData = event.splitStudyData))
                }

                SubjectDetailContract.SubjectDetailEvent.OnClickKebabMenu -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateIsMenuOpen)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnClickTab -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateExamName(event.index))
                }

                is SubjectDetailContract.SubjectDetailEvent.OnClickGetBadgeBottomSheetCloseBtn -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateGetBadgeBottomSheetState(getBadgeBottomSheetState = false))
                }
            }
        }

        override fun reduceState(
            state: SubjectDetailContract.SubjectDetailState,
            reduce: SubjectDetailContract.SubjectDetailReduce,
        ): SubjectDetailContract.SubjectDetailState {
            return when (reduce) {
                is SubjectDetailContract.SubjectDetailReduce.UpdateToDeleteMode -> {
                    state.copy(
                        todoList =
                            state.todoList.map {
                                if (it.cardState == BbangZipCardState.COMPLETE) it else it.copy(cardState = BbangZipCardState.CHECKABLE)
                            },
                        pieceViewType = PieceViewType.DELETE,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateToDefaultMode -> {
                    state.copy(
                        todoList =
                            state.todoList.map {
                                if (it.cardState == BbangZipCardState.COMPLETE) it else it.copy(cardState = BbangZipCardState.DEFAULT)
                            },
                        pieceViewType = PieceViewType.DEFAULT,
                        selectedItemSet = setOf(),
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateDeleteSet -> {
                    Timber.d("[update] ${state.selectedItemSet}")

                    state.copy(
                        selectedItemSet =
                            run {
                                val targetPiece = state.todoList.find { it.pieceId == reduce.pieceId }

                                when (targetPiece?.cardState) {
                                    BbangZipCardState.CHECKED -> {
                                        state.selectedItemSet.plus(targetPiece.pieceId)
                                    }

                                    BbangZipCardState.CHECKABLE -> {
                                        state.selectedItemSet.minus(targetPiece.pieceId)
                                    }

                                    else -> {
                                        state.selectedItemSet
                                    }
                                }
                            },
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateDeleteModeCardState -> {
                    state.copy(
                        todoList =
                            state.todoList.map { item ->
                                if (item.cardState == BbangZipCardState.CHECKABLE && item.pieceId == reduce.pieceId) {
                                    item.copy(cardState = BbangZipCardState.CHECKED)
                                } else if (item.cardState == BbangZipCardState.CHECKED && item.pieceId == reduce.pieceId) {
                                    item.copy(cardState = BbangZipCardState.CHECKABLE)
                                } else {
                                    item
                                }
                            },
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateDefaultCardState -> {
                    state.copy(
                        todoList =
                            state.todoList.map { item ->
                                if (item.cardState == BbangZipCardState.DEFAULT && item.pieceId == reduce.pieceId) {
                                    item.copy(cardState = BbangZipCardState.COMPLETE)
                                } else {
                                    item
                                }
                            },
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateRevertCompleteBottomSheetState -> {
                    state.copy(
                        revertCompleteBottomSheetState = !state.revertCompleteBottomSheetState,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateSelectedId -> {
                    state.copy(
                        selectedItemId = reduce.pieceId,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateCompleteCardState -> {
                    state.copy(
                        todoList =
                            state.todoList.map { item ->
                                if (item.pieceId == state.selectedItemId) {
                                    item.copy(cardState = BbangZipCardState.DEFAULT)
                                } else {
                                    item
                                }
                            },
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateSubjectDetail -> {
                    state.copy(
                        examDate = reduce.subjectDetailInfo.examDate,
                        examDday = reduce.subjectDetailInfo.examDday,
                        motivationMessage = reduce.subjectDetailInfo.motivationMessage,
                        todoList = reduce.subjectDetailInfo.todoList,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateSubjectData -> {
                    state.copy(
                        subjectId = reduce.subjectId,
                        subjectName = reduce.subjectName,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.DeleteSelectedItemSet -> {
                    state
                }

                SubjectDetailContract.SubjectDetailReduce.UpdateIsMenuOpen -> {
                    state.copy(
                        isMenuOpen = !state.isMenuOpen,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateExamName -> {
                    state.copy(
                        examName = if (reduce.index == 0) "중간고사" else "기말고사",
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateGetBadgeList -> {
                    state.copy(
                        badgeList = reduce.badgeList
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateGetBadgeBottomSheetState -> {
                    state.copy(
                        getBadgeBottomSheetState = !currentUiState.getBadgeBottomSheetState
                    )
                }
            }
        }

        private suspend fun initData(subjectId: Int) {
            getSubjectDetail(
                subjectId = subjectId,
                examName = "fin",
            )
        }

        private suspend fun getSubjectDetail(
            subjectId: Int,
            examName: String,
        ) {
            getSubjectDetailUseCase(
                subjectId = subjectId,
                examName = examName,
            ).onSuccess { subjectDetailInfoEntity ->
                updateState(
                    SubjectDetailContract.SubjectDetailReduce.UpdateSubjectDetail(
                        subjectDetailInfo =
                            SubjectDetailInfo(
                                examDate = subjectDetailInfoEntity.examDate,
                                examDday = subjectDetailInfoEntity.examDday,
                                motivationMessage = subjectDetailInfoEntity.motivationMessage,
                                todoList =
                                    subjectDetailInfoEntity.todoList.map { data ->
                                        ToDoCardModel(
                                            pieceId = data.pieceId,
                                            subjectName = data.subjectName,
                                            examName = data.examName,
                                            studyContents = data.studyContents,
                                            startPage = data.startPage,
                                            finishPage = data.finishPage,
                                            deadline = data.deadline,
                                            remainingDays = data.remainingDays,
                                            cardState = if (data.isFinished) BbangZipCardState.COMPLETE else BbangZipCardState.DEFAULT,
                                        )
                                    },
                            ),
                    ),
                )
            }.onFailure { error ->
                Timber.tag("getSubjectDetail").e(error)
            }
        }

        private suspend fun postUnCompleteCardId(
            pieceId: Int,
        ) {
            postUnCompleteCardIdUseCase(
                pieceId = pieceId,
                requestMarkDoneDto = RequestMarkDoneDto(isFinished = false),
            ).onSuccess {
                Timber.tag("markDone").e("완료 성공!")
            }.onFailure { error ->
                Timber.tag("markDone").e(error)
            }
        }

        private suspend fun postCompleteCardId(
            pieceId: Int,
        ) {
            postCompleteCardIdUseCase(
                pieceId = pieceId,
                requestMarkDoneDto = RequestMarkDoneDto(isFinished = true),
            ).onSuccess { data ->
                updateState(SubjectDetailContract.SubjectDetailReduce.UpdateGetBadgeList(badgeList = data.badgeCardList.map { it.toBadge() }))
                updateState(SubjectDetailContract.SubjectDetailReduce.UpdateGetBadgeBottomSheetState(getBadgeBottomSheetState = true))
                Timber.tag("markDone").e("완료 성공!")
            }.onFailure { error ->
                Timber.tag("markDone").e(error)
            }
        }
    }
