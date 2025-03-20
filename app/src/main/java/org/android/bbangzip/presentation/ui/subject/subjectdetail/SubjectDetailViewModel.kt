package org.android.bbangzip.presentation.ui.subject.subjectdetail

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.bbangzip.data.dto.request.RequestMarkDoneDto
import org.android.bbangzip.domain.model.PieceIdEntity
import org.android.bbangzip.domain.usecase.DeleteStudyPieceUseCase
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
        private val deleteStudyPieceUseCase: DeleteStudyPieceUseCase,
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

                SubjectDetailContract.SubjectDetailEvent.OnTrashIconClick -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateToDeleteMode)
                }

                SubjectDetailContract.SubjectDetailEvent.OnCloseIconClick -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateToDefaultMode)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnDeleteModePieceCardClick -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateDeleteModeCardState(event.pieceId))
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateDeleteSet(event.pieceId))
                }

                is SubjectDetailContract.SubjectDetailEvent.OnDefaultModePieceCardClick -> {
                    viewModelScope.launch {
                        postCompleteCardId(event.pieceId)
                    }
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateDefaultModeCardState(event.pieceId))
                    // 사이드 이펙트  스낵바 메시지
                }

                is SubjectDetailContract.SubjectDetailEvent.OnCompleteModePieceCardClick -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateSelectedPieceId(event.pieceId))
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateIsRevertCompleteBottomSheetVisible)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetApproveBtnClick -> {
                    viewModelScope.launch {
                        postUnCompleteCardId(event.pieceId)
                    }
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateCompleteModeCardState)
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateIsRevertCompleteBottomSheetVisible)
                    // TODO 사이드 이펙트 스낵바
                }

                SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDismissBtnClick -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateIsRevertCompleteBottomSheetVisible)
                }

                SubjectDetailContract.SubjectDetailEvent.OnRevertCompleteBottomSheetDismissRequest -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateIsRevertCompleteBottomSheetVisible)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnEnrollMotivateMessageClick -> {
                    setSideEffect(SubjectDetailContract.SubjectDetailSideEffect.NavigateToModifyMotivation(subjectId = event.subjectId, subjectName = event.subjectName))
                }

                is SubjectDetailContract.SubjectDetailEvent.OnModifySubjectNameClick -> {
                    setSideEffect(SubjectDetailContract.SubjectDetailSideEffect.NavigateToModifySubjectName(subjectId = event.subjectId, subjectName = event.subjectName))
                }

                is SubjectDetailContract.SubjectDetailEvent.OnPlusIconClick -> {
                    setSideEffect(SubjectDetailContract.SubjectDetailSideEffect.NavigateToAddStudy(splitStudyData = event.splitStudyData))
                }

                is SubjectDetailContract.SubjectDetailEvent.OnAddStudyCardClick -> {
                    setSideEffect(SubjectDetailContract.SubjectDetailSideEffect.NavigateToAddStudy(splitStudyData = event.splitStudyData))
                }

                is SubjectDetailContract.SubjectDetailEvent.OnAddStudyBtnClick -> {
                    setSideEffect(SubjectDetailContract.SubjectDetailSideEffect.NavigateToAddStudy(splitStudyData = event.splitStudyData))
                }

                SubjectDetailContract.SubjectDetailEvent.OnKebabIconClick -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateIsMenuOpen)
                }

                is SubjectDetailContract.SubjectDetailEvent.OnTabClick -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateExamName(event.index))
                }

                SubjectDetailContract.SubjectDetailEvent.OnDeleteBtnClick -> {
                    viewModelScope.launch { deleteStudyPiece() }
                }

                SubjectDetailContract.SubjectDetailEvent.OnGetBadgeBottomSheetCloseBtnClick -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateIsGetBadgeBottomSheetVisible(getBadgeBottomSheetState = false))
                }

                SubjectDetailContract.SubjectDetailEvent.OnBackIconBtnClick -> {
                    setSideEffect(SubjectDetailContract.SubjectDetailSideEffect.NavigateToBack)
                }

                SubjectDetailContract.SubjectDetailEvent.OnMenuDismissRequest -> {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateIsMenuOpen)
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
                        selectedPieceSet = setOf(),
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateDeleteSet -> {
                    state.copy(
                        selectedPieceSet =
                            run {
                                val targetPiece = state.todoList.find { it.pieceId == reduce.pieceId }

                                when (targetPiece?.cardState) {
                                    BbangZipCardState.CHECKED -> {
                                        state.selectedPieceSet.plus(targetPiece.pieceId)
                                    }

                                    BbangZipCardState.CHECKABLE -> {
                                        state.selectedPieceSet.minus(targetPiece.pieceId)
                                    }

                                    else -> {
                                        state.selectedPieceSet
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

                is SubjectDetailContract.SubjectDetailReduce.UpdateDefaultModeCardState -> {
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

                is SubjectDetailContract.SubjectDetailReduce.UpdateIsRevertCompleteBottomSheetVisible -> {
                    state.copy(
                        isRevertCompleteBottomSheetVisible = !state.isRevertCompleteBottomSheetVisible,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateSelectedPieceId -> {
                    state.copy(
                        selectedPieceId = reduce.pieceId,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateCompleteModeCardState -> {
                    state.copy(
                        todoList =
                            state.todoList.map { item ->
                                if (item.pieceId == state.selectedPieceId) {
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
                        examDDay = reduce.subjectDetailInfo.examDday,
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

                SubjectDetailContract.SubjectDetailReduce.UpdateIsMenuOpen -> {
                    state.copy(
                        isMenuOpen = !state.isMenuOpen,
                    )
                }

                SubjectDetailContract.SubjectDetailReduce.UpdateToEmptyView -> {
                    state.copy(
                        pieceViewType = PieceViewType.EMPTY,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateExamName -> {
                    state.copy(
                        examName = if (reduce.index == 0) "중간고사" else "기말고사",
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateGetBadgeList -> {
                    state.copy(
                        badgeList = reduce.badgeList,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.UpdateIsGetBadgeBottomSheetVisible -> {
                    state.copy(
                        isGetBadgeBottomSheetVisible = !currentUiState.isGetBadgeBottomSheetVisible,
                    )
                }

                is SubjectDetailContract.SubjectDetailReduce.DeleteStudyPiece -> {
                    state.copy(
                        todoList = state.todoList.filter { !reduce.studyPieceId.contains(it.pieceId) },
                    )
                }
            }
        }

        private suspend fun initData(subjectId: Int) {
            getSubjectDetail(
                subjectId = subjectId,
                examName = "mid",
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
                if (subjectDetailInfoEntity.examDday == 999) {
                    updateState(SubjectDetailContract.SubjectDetailReduce.UpdateToEmptyView)
                }
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
                updateState(SubjectDetailContract.SubjectDetailReduce.UpdateIsGetBadgeBottomSheetVisible(getBadgeBottomSheetState = true))
            }.onFailure { error ->
                Timber.tag("markDone").e(error)
            }
        }

        private suspend fun deleteStudyPiece() {
            deleteStudyPieceUseCase(
                pieceIdEntity =
                    PieceIdEntity(
                        piece = currentUiState.selectedPieceSet.map { it },
                    ),
            ).onSuccess {
                updateState(SubjectDetailContract.SubjectDetailReduce.DeleteStudyPiece(currentUiState.selectedPieceSet))
                updateState(SubjectDetailContract.SubjectDetailReduce.UpdateToDefaultMode)
            }.onFailure {
                Timber.tag("[과목 관리]").d("$error")
            }
        }
    }
